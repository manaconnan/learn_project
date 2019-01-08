/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import javafx.scene.input.DataFormat;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018Äê11ÔÂ27ÈÕ 14:18 mazexiang Exp $
 */
public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User(null,12,123.23,new Date());

        Class<User> userClass = User.class;
        Field[] declaredFields = userClass.getDeclaredFields();

        for (Field field : declaredFields){
            System.out.println(field.getName());
            field.setAccessible(true);

            String typeName = field.getGenericType().getTypeName();
            System.out.println("====>"+typeName);
            Object o = field.get(user);

            if (o    instanceof  Integer){
                System.out.println(o);

            }
        }
        String pat1 = "yyyy-MM-dd HH:mm:ss.SSS" ;
        SimpleDateFormat format = new SimpleDateFormat(pat1);
        Date date = new Date();
        System.out.println(Date.class.getName());
        String format1 = format.format(date);
        System.out.println(format1);
    }
}