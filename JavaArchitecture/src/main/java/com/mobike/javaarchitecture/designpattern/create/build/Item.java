package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * Item 指的是食品类 Packing 包装接口
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:20
 */
public interface Item {
    public String name();
    public Packing packing(); // 包装
    public float price();
}
