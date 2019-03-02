package com.mobike.javaarchitecture.designpattern.create.clone;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午4:16
 */
public class CircleShape extends ShapeClone {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
    public CircleShape() {
        type = "Circle";
    }
}
