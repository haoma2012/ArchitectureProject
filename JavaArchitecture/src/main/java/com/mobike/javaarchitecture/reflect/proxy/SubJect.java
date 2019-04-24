package com.mobike.javaarchitecture.reflect.proxy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午11:46
 */
public abstract class SubJect {
    abstract void request();

    public void hello(String string) {
        System.out.println("hello is: " + string);
    }

    public String bye() {
        return "bye";
    }
}
