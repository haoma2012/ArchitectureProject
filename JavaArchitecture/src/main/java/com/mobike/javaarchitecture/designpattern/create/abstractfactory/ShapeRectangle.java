package com.mobike.javaarchitecture.designpattern.create.abstractfactory;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/25 下午9:34
 */

class ShapeRectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("我是画一个矩形！！！");
    }
}
