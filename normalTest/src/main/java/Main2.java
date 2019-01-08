/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

/**
 *
 * @author mazexiang
 * @version $Id: Main2.java, v 0.1 2018年11月29日 14:32 mazexiang Exp $
 */
public class Main2 {


    public static void main(String[] args) {
        String Str_format = "00000000";

        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型

        long num = 123;
        String str = String.format("%05d", num);



    }




}