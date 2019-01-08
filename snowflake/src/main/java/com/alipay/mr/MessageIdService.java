/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

/**
 *
 * @author mazexiang
 * @version $Id: MessageIdService.java, v 0.1 2018��11��16�� 21:32 mazexiang Exp $
 */
public interface MessageIdService {
    /**
     * ����һ����֤ȫ��Ψһ��MessageId
     *
     * @return messageId
     */
    long genMessageId();

    /**
     * ��ʼ������
     *
     * @throws Exception
     */
    void init() throws Exception;
}