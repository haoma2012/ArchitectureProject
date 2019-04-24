package com.mobike.javaarchitecture.designpattern.structure.bridge;

/**
 * 抽象类 持有接口Drawing
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 下午7:20
 */
public abstract class Shape {
    //持有实现的角色Implementor(作图类)
    protected Drawing myDrawing;

    public Shape(Drawing drawing) {
        myDrawing = drawing;
    }

    public abstract void drawing();

    public void drawRantangle() {
        myDrawing.drawRantangle();
    }

    public void drawCircle() {
        myDrawing.drawCircle();
    }
}
