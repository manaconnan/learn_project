/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 *
 * @author mazexiang
 * @version $Id: MessageIdServiceImpl.java, v 0.1 2018年11月16日 21:33 mazexiang Exp $
 */
public class  MessageIdServiceImpl implements MessageIdService {

    private long a ;

    //最大的MachineId，1024个
    private static final long MAX_MACHINE_ID = 1023L;
    //AtomicLongArray 环的大小，可保存200毫秒内，每个毫秒数上一次的MessageId，时间回退的时候依赖与此
    private static final int CAPACITY = 200;
    // 时间戳在messageId中左移的位数
    private static final int TIMESTAMP_SHIFT_COUNT = 22;
    // 机器码在messageId中左移的位数
    private static final int MACHINE_ID_SHIFT_COUNT = 12;
    // 序列号的掩码 2^12 4096
    private static final long SEQUENCE_MASK = 4095L;

    //messageId ，开始的时间戳，start the world，世界初始之日
    private static long START_THE_WORLD_MILLIS;
    //机器码变量
    private long machineId;
    // messageId环，解决时间回退的关键，亦可在多线程情况下减少毫秒数切换的竞争
    private AtomicLongArray messageIdCycle = new AtomicLongArray(CAPACITY);
    //生成MachineIds的实例
    private MachineIdService machineIdService;

    static {
        try {
            //使用一个固定的时间作为start the world的初始值
            START_THE_WORLD_MILLIS = SimpleDateFormat.getDateTimeInstance().parse("2018-01-01 00:00:00").getTime();
        } catch (ParseException e) {
            throw new RuntimeException("init start the world millis failed", e);
        }
    }

    public void setMachineIdService(MachineIdService machineIdService) {
        this.machineIdService = machineIdService;
    }

    /**
     * init方法中通过machineIdService 获取本机的machineId
     * @throws Exception
     */
    @Override
    public void init() throws Exception {


        if (machineId == 0L) {
            String host = InetAddress.getLocalHost().getHostAddress();
            int id =Math.abs( host.hashCode() %(int) MAX_MACHINE_ID );
            machineId = id;
        }
        //获取的machineId 不能超过最大值
        if (machineId <= 0L || machineId > MAX_MACHINE_ID) {
            throw new MessageIdException("the machine id is out of range,it must between 1 and 1023");
        }
    }

    @Override
    public long genMessageId() {
        do {
            // 获取当前时间戳，此时间戳是当前时间减去start the world的毫秒数
            long timestamp = System.currentTimeMillis() - START_THE_WORLD_MILLIS;
            // 获取当前时间在messageIdCycle 中的下标，用于获取环中上一个MessageId
            int index = (int)(timestamp % CAPACITY);
            long messageIdInCycle = messageIdCycle.get(index);
            //通过在messageIdCycle 获取到的messageIdInCycle，计算上一个MessageId的时间戳
            long timestampInCycle = messageIdInCycle >> TIMESTAMP_SHIFT_COUNT;
            // 如果timestampInCycle 并没有设置时间戳，或时间戳小于当前时间，认为需要设置新的时间戳
            if (messageIdInCycle == 0 || timestampInCycle < timestamp) {
                long messageId = timestamp << TIMESTAMP_SHIFT_COUNT | machineId << MACHINE_ID_SHIFT_COUNT;
                // 使用CAS的方式保证在该条件下，messageId 不被重复
                if (messageIdCycle.compareAndSet(index, messageIdInCycle, messageId)) {
                    return messageId;
                }
            }
            // 如果当前时间戳与messageIdCycle的时间戳相等，使用环中的序列号+1的方式，生成新的序列号
            // 如果发生了时间回退的情况，（即timestampInCycle > timestamp的情况）那么不能也更新messageIdCycle 的时间戳，使用Cycle中MessageId+1
            if (timestampInCycle >= timestamp) {
                long sequence = messageIdInCycle & SEQUENCE_MASK;
                if (sequence >= SEQUENCE_MASK) {
                    continue;
                }
                long messageId = messageIdInCycle + 1L;
                // 使用CAS的方式保证在该条件下，messageId 不被重复
                if (messageIdCycle.compareAndSet(index, messageIdInCycle, messageId)) {
                    return messageId;
                }
            }
            // 整个生成过程中，采用的spinLock
        } while (true);
    }

    public static void main(String[] args) throws Exception{
            MessageIdServiceImpl idService = new MessageIdServiceImpl();


        //for (int i =0 ;i<100;i++) {
        //    idService.init();
        //    Thread.sleep(1);
        //
        //    System.out.println(idService.machineId+"===>"+idService.genMessageId());
        //}

        System.out.println(idService.getA());

    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }
}
