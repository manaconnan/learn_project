/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda.compileTest;

import com.alibaba.fastjson.*;
import com.alipay.mazexiang.lambda.learnJavaCompiler.TestInterface;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mazexiang
 * @version $Id: MainProcessor.java, v 0.1 2018年10月25日 17:02 mazexiang Exp $
 */
public class MainProcessor implements TestInterface {

    public static void main(String[] args) {
        MainProcessor mainProcessor = new MainProcessor();

        Map map = new HashMap();
        map.put("name","mzxxxx");
        System.out.println(mainProcessor. getUdfResult(map));
    }

    public String getUdfResult(Map<String, String> map) {

        String name = map.get("name");
        Person person = new Person("mzx",28);
        String jsonString = JSON.toJSONString(person);
        return name+ ":"+jsonString;
    }
}