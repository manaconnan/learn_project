package com.alipay.mazexiang;

public aspect MyAspectJDemo {
    /**
     * �����е�,��־��¼�е�
     */
    pointcut recordLog():call(* HelloWord.sayHello(..));

    /**
     * �����е�,Ȩ����֤(ʵ�ʿ�������־��Ȩ��һ�����ڲ�ͬ��������,�����Ϊ������ʾ)
     */
    pointcut authCheck():call(* HelloWord.sayHello(..));

    /**
     * ����ǰ��֪ͨ!
     */
    before():authCheck(){
        System.out.println("sayHello method before");
    }

    /**
     * �������֪ͨ
     */
    after():recordLog(){
        System.out.println("sayHello method after");
    }
}
