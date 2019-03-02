package com.mobike.javaarchitecture.designpattern.behavior.template;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 下午8:10
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game initialize! start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game started! enjoy the game");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}
