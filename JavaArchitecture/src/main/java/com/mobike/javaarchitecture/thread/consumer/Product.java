package com.mobike.javaarchitecture.thread.consumer;

/**
 * 产品类
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午2:15
 */
public class Product {
    private int id;

    public Product(int id) {
        this.id = id;
    }

    public String toString() {// 重写toString方法
        return "产品：" + this.id;
    }
}
