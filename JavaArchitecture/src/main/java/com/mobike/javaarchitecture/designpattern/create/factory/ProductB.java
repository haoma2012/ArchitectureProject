package com.mobike.javaarchitecture.designpattern.create.factory;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午1:51
 */
public class ProductB implements Product {
    @Override
    public String name() {
        return "My Name is ProductB";
    }

    @Override
    public String price() {
        return "200";
    }
}
