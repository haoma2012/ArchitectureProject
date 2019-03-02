package com.mobike.javaarchitecture.designpattern.create.clone;

/**
 * 原型模式：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 优点：
 * 1、性能提高。 2、逃避构造函数的约束。
 * 缺点：
 * 1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，
 * 但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。
 * 2、必须实现 Cloneable 接口。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午4:13
 */
public abstract class ShapeClone implements Cloneable{
    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
