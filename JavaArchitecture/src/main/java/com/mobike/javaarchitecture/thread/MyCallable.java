package com.mobike.javaarchitecture.thread;

import java.util.concurrent.Callable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-20 17:52
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {

        for (int i = 0; i < 10; i++) {
            System.out.println("MyCallable 执行：x = " + i);

            Thread.sleep(1000);
        }

        return "【MyCallable】线程执行完毕";
    }
}
