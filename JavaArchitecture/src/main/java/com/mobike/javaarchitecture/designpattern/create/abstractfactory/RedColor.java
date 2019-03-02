package com.mobike.javaarchitecture.designpattern.create.abstractfactory;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/25 下午9:38
 */

class RedColor implements Color {
    @Override
    public void fill() {
        System.out.println("填充红色！！！");
    }
}
