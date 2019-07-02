package com.mobike.javaarchitecture.thread.consumer;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午2:14
 */
public class Consumer implements Runnable {

    private Message message;

    public Consumer(Message msg) {
        this.message = msg;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("我是消费者：" + Thread.currentThread().getName() + " : " + this.message.getMessage());
        }
    }
}
