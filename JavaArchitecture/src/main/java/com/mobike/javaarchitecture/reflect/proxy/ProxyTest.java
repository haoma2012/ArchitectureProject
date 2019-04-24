package com.mobike.javaarchitecture.reflect.proxy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午11:48
 */
public class ProxyTest extends SubJect {

    private RealSubJect realSubJect;

    @Override
    void request() {

        if (realSubJect == null) {
            realSubJect = new RealSubJect();
        }

        realSubJect.request();
    }
}
