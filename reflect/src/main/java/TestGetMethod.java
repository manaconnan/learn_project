/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */

import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 * @author mazexiang
 * @version $Id: TestGetMethod.java, v 0.1 2019Äê01ÔÂ07ÈÕ 15:08 mazexiang Exp $
 */
public class TestGetMethod {

    public static void main(String[] args) throws Exception{
        User user = new User("mzx",12,123.23,new Date());
        Method getName = User.class.getMethod("getName");

        Object invoke = getName.invoke(user);

        System.out.println(invoke);

    }
}