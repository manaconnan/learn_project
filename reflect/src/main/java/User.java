/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import java.util.Date;

/**
 *
 * @author mazexiang
 * @version $Id: User.java, v 0.1 2018Äê11ÔÂ27ÈÕ 14:18 mazexiang Exp $
 */
public class User {
    private String name ;
    private int age;
    private Double income;

    private Date birthdate;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public User(){}

    public User(String name, int age, Double income,Date date) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.birthdate = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}