package com.alipay.mazexiang.lambda.compileTest; /**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

/**
 *
 * @author mazexiang
 * @version $Id: Person.java, v 0.1 2018年10月25日 17:00 mazexiang Exp $
 */
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}