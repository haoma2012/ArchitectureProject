package com.mobike.javaarchitecture.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 异常类详解
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/21 上午11:46
 */
public class TestExceptionDemo {

    public static void main(String args[]) {


        System.out.println("Hello world");

        File f = new File("d:/LOL.exe");

        try {
            System.out.println("试图打开 d:/LOL.exe");
            new FileInputStream(f);
            System.out.println("成功打开");
        } catch (FileNotFoundException e) {
            System.out.println("d:/LOL.exe不存在");
            e.printStackTrace();
        }

        try {
            //任何除数不能为0:ArithmeticException
            int k = 5 / 0;

            //下标越界异常：ArrayIndexOutOfBoundsException
            int j[] = new int[5];
            j[10] = 10;

            //空指针异常：NullPointerException
            String str = null;
            str.length();

        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }




    }

    /**
     * 自定义Exception异常
     */
    public class MyException {

    }

    public class Account {

        private int balance; //余额

        public int getBalance() {
            return balance;
        }

        private void deposit() {

        }

        private void withdraw() {

        }
    }
}
