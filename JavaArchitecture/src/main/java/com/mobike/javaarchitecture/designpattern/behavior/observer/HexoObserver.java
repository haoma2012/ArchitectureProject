package com.mobike.javaarchitecture.designpattern.behavior.observer;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午4:20
 */
public class HexoObserver extends Observer {

    public HexoObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hexo String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
