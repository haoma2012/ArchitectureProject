package com.mobike.javaarchitecture.collection.sort;

/**
 * 排序算法工具类
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/23 上午10:34
 */
public class SortUtils {

    // 简单排序:每一趟从待排序的数据元素中选择最小
    //（或最大）的一个元素作为首元素，直到所有元素排完为止，简单选择排序是不稳定排序。
    public static void simpleSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int length = arr.length;
        //
        for (int i = 0; i < length - 1; i++) {
            int min = i;//每一趟循环比较时，min用于存放较小元素的数组下标，这样当前批次比较完毕最终存放的就是此趟内最小的元素的下标，避免每次遇到较小元素都要进行交换。
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            //进行交换，如果min发生变化，则进行交换
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    // 冒泡排序:对相邻的元素进行两两比较，顺序相反则进行交换，
    // 这样，每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序
    public static void bubbleSort(int[] arr) {
        if (arr == null) {
            return;
        }

        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            boolean flag = true;//设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
            for (int j = 0; j < length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
    }

    //直接插入排序基本思想是每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
    public static void insertionSort(int[] arr) {
        if (arr == null) {
            return;
        }

        int length = arr.length;

        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && (arr[j] < arr[j - 1])) {
                swap(arr, j, j - 1);
                j--;
            }

        }
    }

    /**
     * 交换数据
     *
     * @param arr 数组
     * @param a   a
     * @param b   b
     */
    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

}
