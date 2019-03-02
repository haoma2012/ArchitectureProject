package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:58
 */
public class PepsiDrink extends ColdDrink {
    @Override
    public String name() {
        return "PepsiDrink";
    }

    @Override
    public float price() {
        return 8.0f;
    }
}
