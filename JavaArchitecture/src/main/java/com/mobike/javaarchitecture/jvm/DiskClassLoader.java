package com.mobike.javaarchitecture.jvm;

/**
 * 自定义类加载器
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 15:54
 */
public class DiskClassLoader extends ClassLoader {

    private String path;
    public DiskClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }
}
