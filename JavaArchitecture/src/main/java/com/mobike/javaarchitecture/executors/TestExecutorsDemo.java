package com.mobike.javaarchitecture.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程&线程池类使用
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/26 上午10:29
 */
public class TestExecutorsDemo {

    public static void main(String[] args) {

        Executor executor;
        ExecutorService executorService = null;
        Executors executors;


        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
