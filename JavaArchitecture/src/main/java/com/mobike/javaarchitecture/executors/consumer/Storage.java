package com.mobike.javaarchitecture.executors.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 存储类
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午2:14
 */
public class Storage {

    BlockingQueue<Product> queue = new LinkedBlockingQueue<>(10);

    public void push(Product product) {
        try {
            queue.put(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Product pop() {

        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }
}
