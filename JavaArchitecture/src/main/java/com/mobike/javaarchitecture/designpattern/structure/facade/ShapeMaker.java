package com.mobike.javaarchitecture.designpattern.structure.facade;

import com.mobike.javaarchitecture.designpattern.create.abstractfactory.Shape;
import com.mobike.javaarchitecture.designpattern.create.abstractfactory.ShapeCircle;
import com.mobike.javaarchitecture.designpattern.create.abstractfactory.ShapeRectangle;
import com.mobike.javaarchitecture.designpattern.create.abstractfactory.ShapeSquare;

/**
 * 外观模式（Facade Pattern）隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
 * 这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/11 下午5:03
 */
public class ShapeMaker {

    private Shape mCircle;
    private Shape mRectangle;
    private Shape mSquare;

    public ShapeMaker() {
        mCircle = new ShapeCircle();
        mRectangle = new ShapeRectangle();
        mSquare = new ShapeSquare();
    }

    public void drawCircle() {
        if (mCircle != null) {
            mCircle.draw();
        }
    }

    public void drawRectangle() {
        if (mRectangle != null) {
            mRectangle.draw();
        }
    }


    public void drawSquare() {
        if (mSquare != null) {
            mSquare.draw();
        }
    }

}
