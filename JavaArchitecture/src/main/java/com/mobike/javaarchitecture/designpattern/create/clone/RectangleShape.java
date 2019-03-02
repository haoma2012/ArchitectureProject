package com.mobike.javaarchitecture.designpattern.create.clone;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午4:16
 */
public class RectangleShape extends ShapeClone {
    @Override
    void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

    public RectangleShape() {
        type = "Rectangle";
    }
}
