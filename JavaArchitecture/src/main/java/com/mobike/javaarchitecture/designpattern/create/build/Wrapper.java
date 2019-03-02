package com.mobike.javaarchitecture.designpattern.create.build;

/**
 * 包装纸
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午2:21
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
