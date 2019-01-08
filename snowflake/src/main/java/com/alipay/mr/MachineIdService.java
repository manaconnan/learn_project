/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

/**
 *
 * @author mazexiang
 * @version $Id: MachineIdService.java, v 0.1 2018年11月16日 21:37 mazexiang Exp $
 */
public interface MachineIdService {
    /**
     * 生成MachineId的方法
     *
     * @return machineId 机器码
     * @throws  MessageIdException 获取机器码可能因为外部因素失败
     */
    Long getMachineId() throws MessageIdException;
}