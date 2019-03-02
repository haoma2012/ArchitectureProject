package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:58
 */
public class CokeDrink extends ColdDrink {
    @Override
    public String name() {
        return "CokeDrink";
    }

    @Override
    public float price() {
        return 9.0f;
    }
}
