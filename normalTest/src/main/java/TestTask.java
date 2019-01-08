/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

/**
 *
 * @author mazexiang
 * @version $Id: TestTask.java, v 0.1 2018Äê12ÔÂ06ÈÕ 17:05 mazexiang Exp $
 */
public class TestTask implements Runnable{

    public void run() {
        System.out.println("thread naem==> "+Thread.currentThread().getName());
    }
}