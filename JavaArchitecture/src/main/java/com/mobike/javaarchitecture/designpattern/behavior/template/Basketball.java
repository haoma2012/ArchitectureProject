package com.mobike.javaarchitecture.designpattern.behavior.template;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 下午8:10
 */
public class Basketball extends Game {
    @Override
    void initialize() {
        System.out.println("Basketball Game initialize! start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Basketball Game started! enjoy the game");
    }

    @Override
    void endPlay() {
        System.out.println("Basketball Game Finished!");
    }
}
