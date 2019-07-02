package com.mobike.javaarchitecture.model.impl;

import com.mobike.javaarchitecture.model.ICallBack;
import com.mobike.javaarchitecture.model.IConnect;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-18 14:54
 */
public class CallBackImpl implements ICallBack, IConnect {
    @Override
    public void loadSuccess() {
        if (isConnected()) {
            System.out.println("连接成功后，请求成功！！！" );
        } else {
            System.out.println("请检查是否建立连接！！！" );
        }
    }

    @Override
    public void loadFailed() {
        System.out.println("连接失败，请求失败" );
    }



    @Override
    public boolean isConnected() {
        System.out.println("建立起连接了，开始通信吧！！！" );
        return true;
    }
}
