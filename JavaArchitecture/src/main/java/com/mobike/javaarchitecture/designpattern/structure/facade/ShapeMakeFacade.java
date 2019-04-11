package com.mobike.javaarchitecture.designpattern.structure.facade;

import com.mobike.javaarchitecture.designpattern.create.abstractfactory.ShapeSquare;
import com.mobike.javaarchitecture.designpattern.create.clone.CircleShape;

/**
 * 创建一个外观类 外观模式又称门面模式
 * 外观模式（Facade Pattern）隐藏系统的复杂性，
 * 并向客户端提供了一个客户端可以访问系统的接口。
 * 为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午7:55
 */
public class ShapeMakeFacade {
    private CircleShape circleShape;
    private ShapeSquare shapeSquare;
    public ShapeMakeFacade() {
        circleShape = new CircleShape();
        shapeSquare =  new ShapeSquare();
    }

    public void drawCircle() {
        if (circleShape == null) {
            circleShape = new CircleShape();
        }
        circleShape.draw();
    }

    public void drawSquare() {
        if (shapeSquare == null) {
            shapeSquare = new ShapeSquare();
        }
        shapeSquare.draw();
    }
}
