package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 瓶子
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:21
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
