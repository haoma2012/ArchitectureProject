package com.mobike.javaarchitecture.designpattern.behavior;

import com.mobike.javaarchitecture.designpattern.behavior.mediator.ChatRoom;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/27 下午9:51
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage() {
        ChatRoom.sendMessage(this, "");
    }

}
