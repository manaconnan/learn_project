package com.alipay.mazexiang;

public aspect MyAspectJDemo {
    /**
     * 定义切点,日志记录切点
     */
    pointcut recordLog():call(* HelloWord.sayHello(..));

    /**
     * 定义切点,权限验证(实际开发中日志和权限一般会放在不同的切面中,这里仅为方便演示)
     */
    pointcut authCheck():call(* HelloWord.sayHello(..));

    /**
     * 定义前置通知!
     */
    before():authCheck(){
        System.out.println("sayHello method before");
    }

    /**
     * 定义后置通知
     */
    after():recordLog(){
        System.out.println("sayHello method after");
    }
}
