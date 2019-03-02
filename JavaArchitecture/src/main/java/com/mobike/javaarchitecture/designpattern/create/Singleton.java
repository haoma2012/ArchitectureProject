package com.mobike.javaarchitecture.designpattern.create;

/**
 * 单例模式
 * 意图：类只有一个实例，解决了因为多次创建实例导致的系统资源消耗；
 * 优缺点：节约系统资源、提高效率；违背了单一职责，扩展困难
 * 1.饿汉式:类加载就实例化，没有懒加载; 懒汉式线程不安全
 * 2.懒汉式：线程安全 线程不安全 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 3.静态内部类
 * Created by yangdehao@xiaoyouzi.com  on 2018/8/17 下午4:09
 */
public class Singleton {

    public static Singleton instance1 = new Singleton(); // 1.懒汉式

    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance1() { // 2.懒汉式 线程不安全
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static synchronized Singleton getInstance2() { // 3.懒汉式 线程安全 每次调用都要同步 比较慢
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // 4.DCL单例（双重检查锁定）
    public static Singleton getInstance3() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }

        }
        return instance;
    }


    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 5.静态内部类
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public void showMessage() {
        System.out.println("singleton method Hello World!");
    }
}
