package com.mobike.javaarchitecture.reflect.proxy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午11:47
 */
public class RealSubJect extends SubJect {

    @Override
    void request() {
        System.out.print("RealSubJect request");
    }
}
