package com.mobike.javaarchitecture.designpattern.behavior.observer;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午4:20
 */
public class CObserver extends Observer {

    public CObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("CObserver String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
