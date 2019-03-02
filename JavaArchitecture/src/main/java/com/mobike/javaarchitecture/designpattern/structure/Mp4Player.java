package com.mobike.javaarchitecture.designpattern.structure;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午5:06
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
