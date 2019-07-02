package com.mobike.javaarchitecture.collection.stack;

import java.util.LinkedList;

/**
 * 我的队列
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/12 下午9:43
 */
public class MyQueue<T> {

    private LinkedList<T> list = new LinkedList<>();

    // 入队
    public void enqueue(T e) {
        list.add(e);
    }

    // 出对
    public T dequeue() {
        return list.removeFirst();
    }
}
