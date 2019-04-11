package com.mobike.javaarchitecture.designpattern.behavior.chain;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/26 下午3:15
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard File::Logger: " + message);
    }
}
