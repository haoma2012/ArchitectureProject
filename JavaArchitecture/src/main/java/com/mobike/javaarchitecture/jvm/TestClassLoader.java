package com.mobike.javaarchitecture.jvm;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 15:47
 */
public class TestClassLoader {

    public static void main(String[] args) {

        // Bootstrap ClassLoader
        //System.out.println(System.getProperty("sun.boot.class.path"));
        // Extension ClassLoader
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader loader = TestClassLoader.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);//1
            loader = loader.getParent();
        }
    }
}
