package com.mobike.javaarchitecture.model;

import com.mobike.javaarchitecture.model.impl.CallBackImpl;
import com.mobike.javaarchitecture.thread.MyCallable;
import com.mobike.javaarchitecture.thread.MyRunnable;
import com.mobike.javaarchitecture.thread.MyThread;
import com.mobike.javaarchitecture.thread.consumer.Consumer;
import com.mobike.javaarchitecture.thread.consumer.Message;
import com.mobike.javaarchitecture.thread.consumer.Producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-12 10:05
 */
public class TestModelClassDemo {

    public static void main(String[] args) {

        new Person("张三", 18).getInfo();
        // 直接使用类名调用 static方法
        Person.country = "中华人民共和国";
        Person.getCountry();

        Person person = new Person();
        person.setName("李四");
        person.setAge(20);
        person.getInfo();
        // 构造块 代码块 静态代码块
        new Person();

        testMathMethod();
        testObject();
        testStringMethod();

        testThread();

    }

    /**
     * 操作Thread
     */
    private static void testThread() {
        System.out.println("=============Thread 线程 start===========");

        System.out.println("主线程优先级：" + Thread.currentThread().getPriority());

        MyThread thread = new MyThread("A线程");
        thread.start();
        new MyThread("B线程").start();
        new MyThread("C线程").start();


        System.out.println("testThread当前线程：" + thread.getName() + " 默认线程优先级:" + thread.getPriority());

        new Thread(new MyRunnable("D线程")).start();
        new Thread(new MyRunnable("E线程")).start();
        new Thread(new MyRunnable("F线程")).start();

        // 使用FutureTask构造线程返回值
        FutureTask<String> task = new FutureTask<>(new MyCallable());
        new Thread(task).start();
        try {
            System.out.println("FutureTask 执行完毕获取结果" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // 线程中断
        Thread sleepThread = new Thread(() -> {

            System.out.println("菊菊准备开始睡觉了！！！");

            try {
                Thread.sleep(10000);
                System.out.println("菊菊睡醒了！准备开始吃火锅！！！");
            } catch (InterruptedException e) {
                System.out.println("被吵醒了，马上陪我吃火锅！！！");
                e.printStackTrace();
            }

        }, "睡眠线程");
        sleepThread.start();   // 启动线程

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!sleepThread.isInterrupted()) { // 线程是否被中断了true 中断了，false 没有中断
            sleepThread.interrupt();
        }

        Message message = new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();


        System.out.println("=============Thread 线程 end===========");
    }


    /**
     * 字符串方法操作
     */
    private static void testStringMethod() {
        System.out.println("=============字符串start===========");
        String name = "De_hao_name";
        String name2 = "Yang_de";
        String name3 = "De_Hao_name";
        System.out.println("输出字符串长度: " + name.length());
        System.out.println("截取字符串第0个位置: " + name.charAt(0) + " name的位置：" + name.indexOf(name));
        // 判断顺序 => 1.内存地址 2.数据类型 3.字符数组长度 4.单个字符比较
        System.out.println("输出两个字符串是否相等: " + name.equals(name2)
                + " 不比较大小写：" + name.equalsIgnoreCase(name3)
                + " compare比较：" + name.compareTo(name3)
                + "" + name2.toLowerCase() + "  " + name2.toUpperCase());
        String url = "https://www.baidu.com";
        System.out.println(url.startsWith("https")
                + " " + url.endsWith("c0m")
                + " 字符串连接：" + name.concat(url)
                + " 字符串替换：" + name.replace("name", "yang")); // true // true

        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            System.out.println("输出字符：" + aChar);
        }


        String s1 = "HelloWorld";
        String s2 = "HelloWorld";
        String s3 = new String("HelloWorld");
        String s4 = "Hello";
        String s5 = "World";
        String s6 = "Hello" + "World";
        String s7 = s4 + s5;
        // 静态常量池+运行时常量池【可变对象】
        System.out.println(s1 == s2);//1:输出为true
        System.out.println(s1 == s3);//2：输出为false
        System.out.println(s1 == s6);//3：输出为true
        System.out.println(s1 == s7);//4：输出为false
        System.out.println(s1 == s7.intern());//5：输出为true
        System.out.println(s3 == s3.intern());//6：输出为false

        StringBuffer sb = new StringBuffer("Hello ");
        sb.append("World");
        sb.append(1);

        StringBuilder stringBuilder = new StringBuilder("aaa");

        Runtime rn = Runtime.getRuntime();
        System.out.println("Runtime 输出：" + rn + " 虚拟并发数：" + rn.availableProcessors());
        System.out.println("Runtime 输出maxMemory：" + rn.maxMemory());
        System.out.println("Runtime 输出totalMemory：" + rn.totalMemory());
        System.out.println("Runtime 输出freeMemory：" + rn.freeMemory());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 手动调用RunTime的gc()方法 垃圾回收操作
        rn.gc();

        System.out.println("Runtime 输出maxMemory：" + rn.maxMemory());
        System.out.println("Runtime 输出totalMemory：" + rn.totalMemory());
        System.out.println("Runtime 输出freeMemory：" + rn.freeMemory());

        System.out.println("StringBuffer 输出字符串：" + sb.toString() + " 反转：" + sb.reverse());
//        System.currentTimeMillis();
//        System.gc();
//        System.arraycopy(s1, 0, s2, 2, 10);



        System.out.println("=============字符串end===========");
    }

    private static void testObject() {

        Object obj = new Person("张三", 18);
        System.out.println("========obj向上转型========" + obj);
        if (obj instanceof Person) {
            Person person = (Person) obj;
            person.getInfo();

            String dog = "我是狗狗";
            Person person1 = new Person("张三", 18);
            Person person2 = new Person("李四", 19);
            System.out.println("========obj向下转型========" + person + " 输出信息：" + person.toString() + " 对象比较：" + person.equals(person1));
        }


        Integer integer = new Integer(10); // 装箱过程：基本数据类型使用包装类包装
        int value = integer.intValue();           // 拆箱过程：从包装类获取基本数据类型
        System.out.println("使用包装类：" + integer + " 拆箱过程：" + value);

        Integer tenValue = 10; // 自动装箱 JDK1.5开始支持自动
        int num = tenValue;    // 自动拆箱
        tenValue++;
        System.out.println("自动装拆箱过程：" + num * tenValue);

        Object object = 19.2; // double 自动装箱为Double，向上转型为Object
        double numValue = (double) object; // 向下转型为包装类，再自动拆箱
        System.out.println(numValue * 2);


        ICallBack callBack = new CallBackImpl();
        callBack.loading();

        Object callObj = callBack; // 向上转型
        IConnect connect = (IConnect) callObj;
        System.out.println(connect.isConnected());


    }


    /**
     * 定义泛型
     *
     * @param <T>
     */
    class Point<T> {

        private T x;
        private T y;

        public T getX() {
            return x;
        }

        public T getY() {
            return y;
        }

        public void setX(T x) {
            this.x = x;
        }

        public void setY(T y) {
            this.y = y;
        }
    }



    private static void testMathMethod() {
        System.out.println("=============Math方法Start===========");

        double num = round(19.86555, 2);
        System.out.println("数学四舍五入：" + num);


        System.out.println("=============Math方法end===========");


    }

    /**
     *
     * @param num
     * @param scale
     * @return
     */
    private static double round(double num, int scale) {

        return Math.round(num * Math.pow(10, scale)) / Math.pow(10, scale);
    }

}
