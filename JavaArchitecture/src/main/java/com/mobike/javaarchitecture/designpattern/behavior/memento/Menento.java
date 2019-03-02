package com.mobike.javaarchitecture.designpattern.behavior.memento;

/**
 * 备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。
 * 备忘录模式属于行为型模式。
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 上午10:00
 */
public class Menento {
    private String state;

    public Menento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
