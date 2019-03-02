package com.mobike.javaarchitecture.designpattern.structure.decorator;

import com.mobike.javaarchitecture.designpattern.create.abstractfactory.Shape;

/**
 * 创建扩展了 ShapeDecorator 类的实体装饰类
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午7:51
 */
public class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();
        setRedBorder(decoratorShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
