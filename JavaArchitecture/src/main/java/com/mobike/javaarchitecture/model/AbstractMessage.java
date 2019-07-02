package com.mobike.javaarchitecture.model;

/**
 * 抽象类使用
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-18 11:14
 */
public abstract class AbstractMessage {

    private int type; // 消息类型


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    // 定义抽象方法
    public abstract String getMessageInfo();
}
