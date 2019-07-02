package com.mobike.javaarchitecture.thread;

/**
 * 线程调用
 * 1.继承Thread
 * 2.实现Runnable接口
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-20 16:18
 */
public class MyThread extends Thread {

    private String title;
    private int count;

    public MyThread(String title) {
        super(title);
        this.title = title;
    }

    @Override
    public void run() {
        super.run();

        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println("title:" + title + " 操作Count值：" + count);
        }
    }




}


