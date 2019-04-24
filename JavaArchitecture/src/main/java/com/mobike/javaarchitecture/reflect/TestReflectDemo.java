package com.mobike.javaarchitecture.reflect;

import com.mobike.javaarchitecture.collection.Hero;
import com.mobike.javaarchitecture.reflect.proxy.RealSubJect;
import com.mobike.javaarchitecture.reflect.proxy.SubJect;
import com.mobike.javaarchitecture.reflect.proxy.TestInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 反射使用
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午11:38
 */
public class TestReflectDemo {

    public static void testReflect() {
        // 获取ClassName
        try {
            Class c1 = Class.forName("com.mobike.javaarchitecture.collection.Hero");
            System.out.println(c1.getCanonicalName());

            Class c2 = Hero.class;
            System.out.println(c2.getCanonicalName());

            Hero hero = new Hero();
            Class c3 = hero.getClass();
            System.out.println(c3.getCanonicalName());

            Hero green = (Hero) c1.newInstance();
            green.name = "green";
            green.value = 100;

            System.out.println(green.name + green.value);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        SubJect realSubject = new RealSubJect();        // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new TestInvocationHandler(realSubject);        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        SubJect subject = (SubJect) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());
        subject.hello("World");
        String result = subject.bye();
        System.out.println("Result is: " + result);

    }

}
