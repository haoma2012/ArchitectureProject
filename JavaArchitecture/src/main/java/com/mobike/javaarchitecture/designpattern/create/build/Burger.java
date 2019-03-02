package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * Burger 汉堡包 抽象类
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:23
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }


    public abstract float price();

}
