package com.mobike.javaarchitecture.designpattern.behavior.mediator;

import com.mobike.javaarchitecture.designpattern.behavior.User;

import java.util.Date;

/**
 * 中介者模式：
 * 中介者模式（Mediator Pattern）是用来降低多个对象和类之间的通信复杂性。
 * <p>
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/27 下午5:03
 */
public class ChatRoom {

    public static void sendMessage(User user, String message) {
        System.out.println(new Date().toString()
                + " [" + user.getName() + "] : " + message);
    }
}
