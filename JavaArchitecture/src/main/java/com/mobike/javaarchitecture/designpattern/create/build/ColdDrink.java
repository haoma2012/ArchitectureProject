package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 冷饮：抽象类
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:24
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
