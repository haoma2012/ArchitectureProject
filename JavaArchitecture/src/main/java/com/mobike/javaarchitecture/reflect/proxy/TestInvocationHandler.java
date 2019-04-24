package com.mobike.javaarchitecture.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午11:50
 */
public class TestInvocationHandler implements InvocationHandler {
    private Object subject;    // 构造方法，给我们要代理的真实对象赋初值

    public TestInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable {        // 在代理真实对象前我们可以添加一些自己的操作
        System.out.println("Before method");

        System.out.println("Call Method: " + method);        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object obj = method.invoke(subject, args);        // 在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("After method");
        System.out.println();
        return obj;
    }
}
