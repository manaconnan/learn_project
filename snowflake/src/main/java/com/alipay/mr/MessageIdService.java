/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

/**
 *
 * @author mazexiang
 * @version $Id: MessageIdService.java, v 0.1 2018年11月16日 21:32 mazexiang Exp $
 */
public interface MessageIdService {
    /**
     * 生成一个保证全局唯一的MessageId
     *
     * @return messageId
     */
    long genMessageId();

    /**
     * 初始化方法
     *
     * @throws Exception
     */
    void init() throws Exception;
}