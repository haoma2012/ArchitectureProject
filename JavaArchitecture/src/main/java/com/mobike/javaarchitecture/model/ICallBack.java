package com.mobike.javaarchitecture.model;

/**
 * CallBack接口定义
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-18 14:48
 */
public interface ICallBack {

    public static final String INFO = "www.baidu.com";
    /**
     * 请求成功
     */
    void loadSuccess();

    /**
     * 请求失败
     */
    void loadFailed();

    /**
     * 默认方法 1.7后新增
     */
    default void loading(){
        System.out.println("调用默认的方法：loading()");
    }
}
