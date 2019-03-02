package com.mobike.javaarchitecture.designpattern.create.abstractfactory;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/25 下午9:40
 */

class ShapeFactory extends ShapeAbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new ShapeCircle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new ShapeRectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new ShapeSquare();
        }
        return null;
    }
}
