package com.mobike.javaarchitecture.designpattern.structure.proxy;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/26 下午10:48
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}
