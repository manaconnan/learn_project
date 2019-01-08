/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda.learnJavaCompiler;


import com.alibaba.fastjson.JSONObject;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mazexiang
 * @version $Id: JavaCompilerDemo01.java, v 0.1 2018年10月12日 17:23 mazexiang Exp $
 */
public class JavaCompilerDemo01 {
    public static void main(String[] args) throws Exception {


        //String code = "public class Hello{"+
        //                    "public static void main(String[] args){"+
        //                            "System.out.println(\"HelloWorld~~~\"+args[0]);"+
        //
        //                    "}"+
        //              "}";
        //System.out.println(code);
        //String path = "/Users/mazexiang/IdeaProjects/learn_project/hello/";

        //File file = new File(path);
        //
        //if (!file.exists()){
        //    file.getParentFile().mkdirs();
        //    file.createNewFile();
        //}
        //OutputStream os = new FileOutputStream(file);
        //os.write(code.getBytes(),0,code.length());
        //os.flush();
        //os.close();

        File soursefile = new File("/Users/mazexiang/IdeaProjects/learn_project/hello/");
        File compiledClassesFile = new File("/Users/mazexiang/IdeaProjects/learn_project/hello/class__");



        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //直接从文件中获取
        /**compiler.run(1,2,3,4)
         * @parameters:
        1.in → "standard" input; use System.in（键盘输入） if null
        2.out → "standard" output; use System.out（输出到控制台） if null
        3.err → "standard" error; use System.err if null
        4.arguments → arguments to pass to the tool
         */
        File[] files = soursefile.listFiles();
        Arrays.stream(files).forEach(file -> {

            if (file.getName().endsWith(".java")) {

                int result = compiler.run(null, null, null, file.getAbsolutePath());

                System.out.println(result == 0 ? "编译成功" : "编译失败");
            }

        });
        String jsonString = JSONObject.toJSONString("s");

        //通过Runtime类动态运行编译好的类
        Runtime rt = Runtime.getRuntime();
        Process pro = rt.exec("java -cp /Users/mazexiang/IdeaProjects/learn_project/hello Hello xx");//实际上已经执行了
        //让结果输出到控制台
        InputStream in = pro.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String temp = "";
        while((temp=reader.readLine())!=null){
            System.out.println(temp);
        }

        System.out.println("======================");

        //通过类加载器来动态运行编译好的类
        URL[] urls = new URL[]{new URL("file",null,"/Users/mazexiang/IdeaProjects/learn_project/hello/")};

        URLClassLoader loader = new URLClassLoader(urls);
        Class myTest = loader.loadClass("MyTest");
        Map<String,String> map = new HashMap<>();
        map.put("key1","mzx");
        TestInterface testInterface = (TestInterface) myTest.newInstance();
        String udfResult = testInterface.getUdfResult(map);
        //调用加载类的main方法
        System.out.println(udfResult);
        System.out.println(map);

        Class hello = loader.loadClass("Hello");
        Method m = hello.getMethod("main", String[].class);
        m.invoke(null, (Object)new String[]{"aa","bb"});
        //注意上面的代码，如果不加（Object）转型的话，
        //则会编译成：m.invoke(null,"aa","bb"),就发生了参数个数不匹配的问题。


    }
}