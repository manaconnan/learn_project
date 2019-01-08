/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda.learnJavaCompiler;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018年10月25日 17:44 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) throws Exception {

        try {
            String filePath = "/Users/mazexiang/IdeaProjects/learn_project/compileTest/src";
            String sourceDir = "/Users/mazexiang/IdeaProjects/learn_project/compileTest/src";
            String jarPath = "/Users/mazexiang/temp/jars";
            String targetDir = "/Users/mazexiang/temp/class";
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            DynamicCompilerUtil dynamicCompilerUtil = new DynamicCompilerUtil();
            boolean compilerResult = dynamicCompilerUtil.compiler("UTF-8", dynamicCompilerUtil.getJarFiles(jarPath), filePath, sourceDir, targetDir, diagnostics);
            if (compilerResult) {
                System.out.println("编译成功");
            } else {
                System.out.println("编译失败");
                for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                    // System.out.format("%s[line %d column %d]-->%s%n", diagnostic.getKind(), diagnostic.getLineNumber(),
                    // diagnostic.getColumnNumber(),
                    // diagnostic.getMessage(null));
                    System.out.println(diagnostic.getMessage(null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        //通过类加载器来动态运行编译好的类 传入目录,结尾是"/"
        URL[] urls = new URL[]{new URL("file",null,"/Users/mazexiang/temp/class/")};

        URLClassLoader loader = new URLClassLoader(urls);

        // 需要传入类名的全路径
        Class myTest = loader.loadClass("com.alipay.mazexiang.compileTest.MainProcessor");
        Map<String,String> map = new HashMap<>();
        map.put("name","mzx");
        TestInterface testInterface = (TestInterface) myTest.newInstance();
        String udfResult = testInterface.getUdfResult(map);
        //调用加载类的main方法
        System.out.println(udfResult);
        System.out.println(map);

    }
}