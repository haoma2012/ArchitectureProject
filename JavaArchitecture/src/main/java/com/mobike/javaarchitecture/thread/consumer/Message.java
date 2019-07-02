package com.mobike.javaarchitecture.thread.consumer;

/**
 * 消息中心 Producer & Consumer 共同操作它
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-22 17:27
 */
public class Message {

    private String title;
    private String content;
    private boolean flags = true; // 表示生产消费值形式 true 允许生产，不允许消费；false 允许消费 不允许生产

    public synchronized void setMessage(String title, String content) {
        if (!flags) { // 还没有生产 需要等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        this.content = content;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flags = false; // 生产完成
        this.notify();// 唤起消费者消费
    }

    public synchronized String getMessage() {
        if (flags) { // 还未生产 去生产吧 需要等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            return title + " --- " + content;
        } finally { // 不管如何都要执行
            flags = true;
            this.notify(); // 唤起生产者生产
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
