package com.mobike.javaarchitecture.executors.consumer;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午2:14
 */
public class Consumer implements Runnable {

    private String name;
    private Storage s = null;

    public Consumer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println(name + "准备消费产品.");
            Product product = s.pop();
            System.out.println(name + "已消费(" + product.toString() + ").");
            System.out.println("===============");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
