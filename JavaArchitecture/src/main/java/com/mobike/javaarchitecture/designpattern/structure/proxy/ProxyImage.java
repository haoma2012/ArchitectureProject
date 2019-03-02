package com.mobike.javaarchitecture.designpattern.structure.proxy;

/**
 * 代理模式（Proxy Pattern:
 * 为其他对象提供一种代理以控制对这个对象的访问。
 * Created by yangdehao@xiaoyouzi.com  on 2019/2/27 上午9:14
 */
public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName) {
        //this.realImage = realImage;
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
