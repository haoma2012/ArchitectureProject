package com.mobike.javaarchitecture.model;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-12 09:50
 */
public class Person {
    /**
     * 属性设置成private 对属性封装 实现set、get方法
     */
    private String name;   // 姓名
    private int age;       // 年龄
    private double salary; // 工资
    public static String country;// 国家
    // 数组 二维数组双层循环for or forEach
    public int[] sepNo = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    public int[][] data = new int[][]{{1, 2, 3, 4}, {5, 6, 7}, {8, 9, 0}};

    // 无参构造
    public Person() {
        //this("", 18, 3000.00);
        System.out.println("我是构造函数代码块");
        ///System.out.println("人员：" + getName() + " 年龄：" + getAge());
    }

    /**
     * 我是构造块
     */ {
        System.out.println("我是构造块代码");
    }

    /**
     * 我是静态块
     */
    static {
        System.out.println("【静态块】我是静态块代码");
    }

    public Person(String name) {
        //System.out.println("人员：" + getName() + " 年龄：" + getAge());
        this(name, 0, 6000.00);
    }

    public Person(String name, int age) {
        this(name, age, 9000.00);
        // System.out.println("人员：" + getName() + " 年龄：" + getAge());
//        this.name = name;
//        this.age = age;
//        this.setName(name);
//        this.setAge(age);
    }

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        System.out.println("人员：" + getName() +
                " 年龄：" + getAge() +
                " 工资：" + getSalary() +
                " 国家：" + getCountry());

    }

    public int getAge() {
        return age;
    }

    // 设置&修改值
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static String getCountry() {
        return country;
    }

    public void getInfo() {
        String result = "人员：" + getName() + " 年龄：" + getAge() + "、工资：" + getSalary();
        System.out.println(result);
    }

    @Override
    public String toString() { // 重写toString方法输出类信息
        return "人员：" + getName() + " 年龄：" + getAge() + "、工资：" + getSalary();
    }

    @Override
    public boolean equals(Object obj) { // 重写类的equals方法 输出对象比较
        if (obj == null) { // 空值判断
            return false;
        }

        if (!(obj instanceof Person)) { // 类型判断
            return false;
        }

        Person person = (Person) obj; // 向下转型为Person
        return this.getName().equals(person.getName()) && (getAge() == person.getAge());

    }
}
