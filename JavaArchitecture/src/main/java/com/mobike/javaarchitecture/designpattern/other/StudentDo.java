package com.mobike.javaarchitecture.designpattern.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Student Model
 * 传输对象模式（Transfer Object Pattern）用于从客户端向服务器一次性传递带有多个属性的数据。
 * 传输对象也被称为数值对象。
 * 传输对象是一个具有 getter/setter 方法的简单的 POJO 类，它是可序列化的，所以它可以通过网络传输。
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/5 下午3:31
 */
public class StudentDo {

    //列表是当作一个数据库
    List<StudentModel> students;

    public StudentDo() {
        students = new ArrayList<>();
        StudentModel student1 = new StudentModel("Robert", 0);
        StudentModel student2 = new StudentModel("John", 1);
        students.add(student1);
        students.add(student2);
    }

    private void checkDataNotNull() {
        if (students == null) {
            students = new ArrayList<>();
        }
    }

    public void addData(StudentModel model) {
        if (model == null) {
            return;
        }
        checkDataNotNull();
        students.add(model);
    }


    public void deleteStudent(StudentModel student) {
        checkDataNotNull();
        students.remove(student.getRollNo());
        System.out.println("Student: Roll No "
                + student.getRollNo() + ", deleted from database");
    }

    public void updateStudent(StudentModel student) {
        students.get(student.getRollNo()).setName(student.getName());
        System.out.println("Student: Roll No "
                + student.getRollNo() + ", updated in the database");
    }


    /**
     * 从数据库检索所有数据
     *
     * @return List
     */
    public List<StudentModel> getAllStudents() {
        checkDataNotNull();
        return students;
    }

    public StudentModel getStudentWithNo(int rollNo) {
        checkDataNotNull();
        return students.get(rollNo);
    }

}
