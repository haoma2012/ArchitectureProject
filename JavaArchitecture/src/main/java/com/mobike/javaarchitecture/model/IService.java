package com.mobike.javaarchitecture.model;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-19 10:00
 */
public interface IService extends IConnect,ICallBack {

      default void testStaticMethod() {
        if (isConnected()) {
            System.out.println("testStaticMethod");
        }
    }
}
