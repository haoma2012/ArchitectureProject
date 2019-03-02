package com.mobike.javaarchitecture.designpattern.behavior.observer;

/**
 * 对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。
 * 比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午4:15
 */
public abstract class Observer {
    protected  Subject subject;
    public abstract void update();
}
