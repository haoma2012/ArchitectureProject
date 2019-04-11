package com.mobike.javaarchitecture.designpattern.other;

/**
 * 需要传输的数据Model
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/5 下午3:31
 */
public class StudentModel {

    private String name;
    private int rollNo;

    public StudentModel(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getRollNo() {
        return rollNo;
    }
}
