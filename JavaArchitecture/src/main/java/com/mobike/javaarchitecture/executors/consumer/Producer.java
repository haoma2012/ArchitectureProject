package com.mobike.javaarchitecture.executors.consumer;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午2:14
 */
public class Producer implements Runnable {

    private String name;
    private Storage s = null;

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }


    @Override
    public void run() {
        while (true) {
            Product product = new Product(1000);
            System.out.println(name + "准备生产产品.");
            s.push(product);
            System.out.println(name + "已生产(" + product.toString() + ").");
            System.out.println("===============");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
