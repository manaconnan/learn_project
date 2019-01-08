/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author mazexiang
 * @version $Id: Dataloader.java, v 0.1 2018Äê11ÔÂ01ÈÕ 16:53 mazexiang Exp $
 */
public class Dataloader {
    private static Map<Integer,List<String>> mockDataSource = new HashMap<Integer, List<String>>();

    static {
        List<String> strings = Arrays.asList("abc","qwer","sdf23");
        List<String> strings1 = Arrays.asList("adc","zcf","xwq");
        List<String> strings2 = Arrays.asList("poi","mzx","muran");
        mockDataSource.put(0,strings);
        mockDataSource.put(1,strings1);
        mockDataSource.put(2,strings2);
    }
    public void getItems(Integer key, Consumer<String> consumer) throws Exception {
        List<String> list = mockDataSource.get(key);
        if(list != null){
            list.stream().forEach(consumer);
        }
    }
}