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
 * @version $Id: MessageIdServiceImpl.java, v 0.1 2018��11��16�� 21:33 mazexiang Exp $
 */
public class  MessageIdServiceImpl implements MessageIdService {

    private long a ;

    //����MachineId��1024��
    private static final long MAX_MACHINE_ID = 1023L;
    //AtomicLongArray ���Ĵ�С���ɱ���200�����ڣ�ÿ����������һ�ε�MessageId��ʱ����˵�ʱ���������
    private static final int CAPACITY = 200;
    // ʱ�����messageId�����Ƶ�λ��
    private static final int TIMESTAMP_SHIFT_COUNT = 22;
    // ��������messageId�����Ƶ�λ��
    private static final int MACHINE_ID_SHIFT_COUNT = 12;
    // ���кŵ����� 2^12 4096
    private static final long SEQUENCE_MASK = 4095L;

    //messageId ����ʼ��ʱ�����start the world�������ʼ֮��
    private static long START_THE_WORLD_MILLIS;
    //���������
    private long machineId;
    // messageId�������ʱ����˵Ĺؼ�������ڶ��߳�����¼��ٺ������л��ľ���
    private AtomicLongArray messageIdCycle = new AtomicLongArray(CAPACITY);
    //����MachineIds��ʵ��
    private MachineIdService machineIdService;

    static {
        try {
            //ʹ��һ���̶���ʱ����Ϊstart the world�ĳ�ʼֵ
            START_THE_WORLD_MILLIS = SimpleDateFormat.getDateTimeInstance().parse("2018-01-01 00:00:00").getTime();
        } catch (ParseException e) {
            throw new RuntimeException("init start the world millis failed", e);
        }
    }

    public void setMachineIdService(MachineIdService machineIdService) {
        this.machineIdService = machineIdService;
    }

    /**
     * init������ͨ��machineIdService ��ȡ������machineId
     * @throws Exception
     */
    @Override
    public void init() throws Exception {


        if (machineId == 0L) {
            String host = InetAddress.getLocalHost().getHostAddress();
            int id =Math.abs( host.hashCode() %(int) MAX_MACHINE_ID );
            machineId = id;
        }
        //��ȡ��machineId ���ܳ������ֵ
        if (machineId <= 0L || machineId > MAX_MACHINE_ID) {
            throw new MessageIdException("the machine id is out of range,it must between 1 and 1023");
        }
    }

    @Override
    public long genMessageId() {
        do {
            // ��ȡ��ǰʱ�������ʱ����ǵ�ǰʱ���ȥstart the world�ĺ�����
            long timestamp = System.currentTimeMillis() - START_THE_WORLD_MILLIS;
            // ��ȡ��ǰʱ����messageIdCycle �е��±꣬���ڻ�ȡ������һ��MessageId
            int index = (int)(timestamp % CAPACITY);
            long messageIdInCycle = messageIdCycle.get(index);
            //ͨ����messageIdCycle ��ȡ����messageIdInCycle��������һ��MessageId��ʱ���
            long timestampInCycle = messageIdInCycle >> TIMESTAMP_SHIFT_COUNT;
            // ���timestampInCycle ��û������ʱ�������ʱ���С�ڵ�ǰʱ�䣬��Ϊ��Ҫ�����µ�ʱ���
            if (messageIdInCycle == 0 || timestampInCycle < timestamp) {
                long messageId = timestamp << TIMESTAMP_SHIFT_COUNT | machineId << MACHINE_ID_SHIFT_COUNT;
                // ʹ��CAS�ķ�ʽ��֤�ڸ������£�messageId �����ظ�
                if (messageIdCycle.compareAndSet(index, messageIdInCycle, messageId)) {
                    return messageId;
                }
            }
            // �����ǰʱ�����messageIdCycle��ʱ�����ȣ�ʹ�û��е����к�+1�ķ�ʽ�������µ����к�
            // ���������ʱ����˵����������timestampInCycle > timestamp���������ô����Ҳ����messageIdCycle ��ʱ�����ʹ��Cycle��MessageId+1
            if (timestampInCycle >= timestamp) {
                long sequence = messageIdInCycle & SEQUENCE_MASK;
                if (sequence >= SEQUENCE_MASK) {
                    continue;
                }
                long messageId = messageIdInCycle + 1L;
                // ʹ��CAS�ķ�ʽ��֤�ڸ������£�messageId �����ظ�
                if (messageIdCycle.compareAndSet(index, messageIdInCycle, messageId)) {
                    return messageId;
                }
            }
            // �������ɹ����У����õ�spinLock
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
