package com.mobike.javaarchitecture.designpattern.create.abstractfactory;

/**
 * 抽象基类
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/25 下午9:39
 */

public  abstract class ShapeAbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
