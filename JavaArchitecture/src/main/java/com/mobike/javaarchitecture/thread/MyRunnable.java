package com.mobike.javaarchitecture.thread;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-20 16:37
 */
public class MyRunnable implements Runnable {
    private String title;
    private int count;

    public MyRunnable(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            count++;
            String name = Thread.currentThread().getName();
            System.out.println("线程名："+ name + " title:" + title + " 操作Count值：" + count);
        }
    }
}
