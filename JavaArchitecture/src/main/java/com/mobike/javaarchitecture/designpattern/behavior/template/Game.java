package com.mobike.javaarchitecture.designpattern.behavior.template;

/**
 * 模板模式：一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，
 * 但调用将以抽象类中定义的方式进行
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 下午8:08
 */
public abstract class Game {

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
