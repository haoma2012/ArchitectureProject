package com.mobike.javaarchitecture.designpattern.structure.decorator;

import com.mobike.javaarchitecture.designpattern.create.abstractfactory.Shape;

/**
 * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，
 * 同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，装饰器模式相比生成子类更为灵活。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午7:44
 */
public abstract class ShapeDecorator {
    protected Shape decoratorShape;

    public ShapeDecorator(Shape shape) {
        decoratorShape = shape;
    }

    public void draw() {
        if (decoratorShape == null) {
            return;
        }
        decoratorShape.draw();
    }
}
