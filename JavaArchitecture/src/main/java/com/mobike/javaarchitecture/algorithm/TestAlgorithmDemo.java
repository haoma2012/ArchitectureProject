package com.mobike.javaarchitecture.algorithm;

import com.mobike.javaarchitecture.collection.sort.SortUtils;

/**
 * 算法排序整合
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-16 14:21
 */
public class TestAlgorithmDemo {

    public static void main(String[] args) {

        int[] arrays = {12, 45, 9, 67, 455, 50, 29, 66, 55, 18, 99, 100, 56};

        SortUtils.bubbleSort(arrays);

        int num = 2147483647;
        long temp = num + 2L;
        num+=2L;
        System.out.println(num);
        System.out.println("temp = " + temp);
        testNum();
    }




    private static void testNum() {
        int i=1;
        int j=i++;
        System.out.println("i = " + i + " j = " + j);
        if (i==(++j)&&(i++)==j) {
            i+=j;
        }
        System.out.println("i = " + i);

        int num = 0;
        for (int x=0;i<10;i++) {
            num+=x;
            if (x % 3==0) {
                break;
            }
        }
        System.out.println("num = " + num);

        int max = 50;
        max = max++ * 2;

        System.out.println("max = " + max);


    }
}
