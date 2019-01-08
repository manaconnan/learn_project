/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018年11月08日 15:21 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> longList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

        int size = longList.size();
        int split = size / 4;
        System.out.println(split);
        for (int i =0 ; i<4;i++){
            if (i<3){
                List<Integer> integers = longList.subList(i * split, (i + 1) * split);
                System.out.println(JSON.toJSONString(integers));
            }
            else {
                List<Integer> integers = longList.subList(i * split, size);
                System.out.println(JSON.toJSONString(integers));
            }
        }

        System.out.println(String.format("我爱上你[%s],爱你{%d}年","中国",1000));


        String s = "2158";

        System.out.println(Long.valueOf(s));

        System.out.println();



    }
}