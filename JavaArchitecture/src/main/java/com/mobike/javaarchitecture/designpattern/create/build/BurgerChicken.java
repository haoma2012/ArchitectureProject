package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 鸡肉堡
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:33
 */
public class BurgerChicken extends Burger {

    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.0f;
    }
}
