/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

/**
 *
 * @author mazexiang
 * @version $Id: MachineIdService.java, v 0.1 2018��11��16�� 21:37 mazexiang Exp $
 */
public interface MachineIdService {
    /**
     * ����MachineId�ķ���
     *
     * @return machineId ������
     * @throws  MessageIdException ��ȡ�����������Ϊ�ⲿ����ʧ��
     */
    Long getMachineId() throws MessageIdException;
}