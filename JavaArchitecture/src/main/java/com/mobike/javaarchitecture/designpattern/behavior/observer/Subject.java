package com.mobike.javaarchitecture.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午4:16
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public int getState() {
        return state;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
