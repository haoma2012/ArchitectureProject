package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 素汉堡
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:33
 */
public class BurgerVeg extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
