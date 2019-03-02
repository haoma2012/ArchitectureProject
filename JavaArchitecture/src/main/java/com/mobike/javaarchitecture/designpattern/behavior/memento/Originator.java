package com.mobike.javaarchitecture.designpattern.behavior.memento;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午1:53
 */
public class Originator {

    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Menento saveStateToMemento() {
        return new Menento(state);
    }

    public void getStateFromMemento(Menento menento) {
        if (menento == null) {
            menento = new Menento(state);
        }
        state = menento.getState();
    }
}
