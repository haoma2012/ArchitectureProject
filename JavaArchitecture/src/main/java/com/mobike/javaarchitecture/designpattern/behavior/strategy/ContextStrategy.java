package com.mobike.javaarchitecture.designpattern.behavior.strategy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 上午10:02
 */
public class ContextStrategy {

    private Strategy strategy;

    public ContextStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        if (strategy != null) {
            return strategy.doOperation(num1, num2);
        }

        return 0;
    }
}
