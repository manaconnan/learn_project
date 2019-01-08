/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mr;

/**
 *
 * @author mazexiang
 * @version $Id: RedisMachineIdServiceImpl.java, v 0.1 2018Äê11ÔÂ16ÈÕ 21:37 mazexiang Exp $
 */
public class RedisMachineIdServiceImpl implements MachineIdService {

    private int i ;
    public RedisMachineIdServiceImpl(int i){
        this.i = i;
    }
    @Override
    public Long getMachineId() throws MessageIdException {
        return 10L+i;
    }
}