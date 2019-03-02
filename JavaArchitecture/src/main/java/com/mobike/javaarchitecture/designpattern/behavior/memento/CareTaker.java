package com.mobike.javaarchitecture.designpattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/28 下午2:14
 */
public class CareTaker {

    private List<Menento> menentoList = new ArrayList<>();

    public void add(Menento menento) {
        if (menento != null) {
            menentoList.add(menento);
        }
    }

    public Menento get(int index) {
        return menentoList.get(index);
    }
}
