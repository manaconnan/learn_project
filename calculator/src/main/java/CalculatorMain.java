/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author mazexiang
 * @version $Id: CalculatorMain.java, v 0.1 2019年02月18日 15:43 mazexiang Exp $
 */
public class CalculatorMain {
    protected  final static String dataPattern = ".*[0-9]+月[0-9]+日.*";
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir")+"/本月记账.txt";
        File file = new File(path);
        List<String> list = FileUtils.readLines(file);
        Integer reduce = list.stream()
                .filter(s -> !Pattern.matches(dataPattern, s))
                .map(s -> parseIntFromString(s))
                .reduce(0, (total, next) -> {
                    total = total + next;
                    return total;
        });

        System.out.println("total = "+ reduce);
    }

     private static int parseIntFromString(String string){
         String num = "[0-9]";
         char[] chars = string.toCharArray();
         StringBuilder builder = new StringBuilder();
         for (char c : chars){
             if(!Character.isDigit(c)){
                 if (builder.length()!=0){
                     break;
                 }
                 continue;
             }
             builder.append(c);
         }
         if (builder.length()==0){
             return 0;
         }
         return Integer.parseInt(builder.toString());
     }
}