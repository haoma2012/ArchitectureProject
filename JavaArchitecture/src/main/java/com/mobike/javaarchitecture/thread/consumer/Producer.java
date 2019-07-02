package com.mobike.javaarchitecture.thread.consumer;

/**
 * 生产者
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-22 17:37
 */
public class Producer implements Runnable {

    private Message message;

    public Producer(Message msg) {
        this.message = msg;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                message.setMessage("王建", "宇宙大帅哥");
            } else {
                message.setMessage("小高", "猥琐第一人！");
            }
            System.out.println("我是生产者：" + Thread.currentThread().getName() + " 正在生产：" + i);
        }
    }
}
