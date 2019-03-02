package com.mobike.javaarchitecture.designpattern.create.clone;

import java.util.Hashtable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午4:17
 */
public class ShapeCache {
    private static Hashtable<String, ShapeClone> shapeMap = new Hashtable<>();

    public static ShapeClone getShape(String shapeId) {
        ShapeClone cachedShape = shapeMap.get(shapeId);
        return (ShapeClone) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        CircleShape circle = new CircleShape();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);

//        Square square = new Square();
//        square.setId("2");
//        shapeMap.put(square.getId(),square);

        RectangleShape rectangle = new RectangleShape();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(),rectangle);
    }
}
