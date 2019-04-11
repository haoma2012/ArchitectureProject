package com.mobike.framework.event;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/20 下午5:27
 */
public class MessageEvent extends BaseEvent{

    private String title;

    public MessageEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
