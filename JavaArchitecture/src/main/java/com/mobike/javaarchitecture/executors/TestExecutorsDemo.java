package com.mobike.javaarchitecture.executors;

import com.mobike.javaarchitecture.executors.consumer.Consumer;
import com.mobike.javaarchitecture.executors.consumer.Producer;
import com.mobike.javaarchitecture.executors.consumer.Storage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程&线程池类使用
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/26 上午10:29
 */
public class TestExecutorsDemo {

    public static void main(String[] args) {


        Storage storage = new Storage();
        ExecutorService service = Executors.newCachedThreadPool();

        Producer p = new Producer("张三", storage);
        Producer p2 = new Producer("李四", storage);
        Consumer c = new Consumer("王五", storage);
        Consumer c2 = new Consumer("老刘", storage);
        Consumer c3 = new Consumer("老林", storage);
        service.submit(p);
        //service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);
    }











}
