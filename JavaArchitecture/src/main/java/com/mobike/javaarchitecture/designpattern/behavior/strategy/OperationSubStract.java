package com.mobike.javaarchitecture.designpattern.behavior.strategy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/1 上午9:59
 */
public class OperationSubStract implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
