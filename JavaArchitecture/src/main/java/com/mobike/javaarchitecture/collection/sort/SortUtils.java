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

        System.out.println("排序前：");
        for (int i1 : arr) System.out.print(i1 + "\t");

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

        System.out.println("排序后：");
        for (int i1 : arr) System.out.print(i1 + "\t");
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

    /**
     * 先选取一个小于n的整数d（步长），然后按照步长d将待排序序列分为d组，
     * 从第一个记录开始间隔为d的为一个组。然后对各组内进行直接插入排序，
     * 一趟过后，间隔为d的序列有序，随着有序性的改善，减少步长d重复进行 。
     * 直到d=1使得间隔为1的记录有序，也就达到了整体有序
     */
    public static void shelSort() {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        //希尔排序
        int d = a.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i = i + d) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //归并排序
    public static void funMergeSort() {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("归并排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        funMergeSort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 归并排序
     * 分治法的一个典型应用，它的主要思想是：将待排序序列分为两部分，
     * 对每部分递归地应用归并排序，在两部分都排好序后进行合并
     * @param array 数组
     */
    public static void funMergeSort(int[] array) {
        if (array.length > 1) {
            int length1 = array.length / 2;
            int[] array1 = new int[length1];
            System.arraycopy(array, 0, array1, 0, length1);
            funMergeSort(array1);

            int length2 = array.length - length1;
            int[] array2 = new int[length2];
            System.arraycopy(array, length1, array2, 0, length2);
            funMergeSort(array2);

            int[] datas = merge(array1, array2);
            System.arraycopy(datas, 0, array, 0, array.length);
        }

    }

    //合并两个数组
    private static int[] merge(int[] list1, int[] list2) {

        int[] list3 = new int[list1.length + list2.length];

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        while (count1 < list1.length && count2 < list2.length) {

            if (list1[count1] < list2[count2]) {
                list3[count3++] = list1[count1++];
            } else {
                list3[count3++] = list2[count2++];
            }
        }

        while (count1 < list1.length) {
            list3[count3++] = list1[count1++];
        }

        while (count2 < list2.length) {
            list3[count3++] = list2[count2++];
        }

        return list3;
    }


}
