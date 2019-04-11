package com.mobike.javaarchitecture.collection;

/**
 * 线性数据结构：
 * 1.数组：一种连续存储线性结构，元素类型相同，大小相等
 * 数组的优点：
 * <p>
 * 存取速度快
 * 数组的缺点：
 * <p>
 * 事先必须知道数组的长度
 * 插入删除元素很慢
 * 空间通常是有限制的
 * 需要大块连续的内存块
 * 插入删除元素的效率很低
 * 2.链表：链表是离散存储线性结构
 * 链表优点：
 * 空间没有限制
 * 插入删除元素很快
 * <p>
 * 链表缺点：
 * 存取速度很慢
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/11 下午8:20
 */
public class LinearCollections {
    // 创建数组
    private int[] arrays = new int[10];

    public static void main(String[] args) {

        // 求和
        sum();
        // 排序算法测试

    }


    public void insertData(int[] arrays) {
        if (arrays == null) {
            arrays = new int[10];
        }
        // 插入数据
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = i;
        }
    }

    public void deleteNum(int num) {
        if (arrays != null) {
            for (int temp : arrays) {
                if (temp == num) {

                }
            }
        }
    }

    public int queryNum(int searchKey) {
        int position = -1;
        if (arrays != null) {
            int length = arrays.length;
            int i = 0;
            while (i < length) {
                if (searchKey == arrays[i]) {
                    position = i;
                    break;
                }
                i++;
            }


        }
        return position;
    }

    /**
     * 二分查找：针对有序数组 折半查找
     *
     * @param data      数组数据 有序数组
     * @param searchKey 查找数据
     */
    public int binarySearch(int[] data, int searchKey) {
        if (data == null) {
            return -1;
        }
        int length = data.length;
        int lowerBound = 0;
        int upperBound = length - 1;

        int curIn;
        while (lowerBound < upperBound) {
            // 取中间位置
            curIn = lowerBound + (upperBound - lowerBound) / 2;
            if (data[curIn] == searchKey) {
                return curIn;
            } else if (data[curIn] < searchKey) {
                lowerBound = curIn + 1;
            } else if (data[curIn] > searchKey) {
                upperBound = curIn - 1;
            }
        }
        return -1;
    }

    /**
     * 使用递归 二分查找
     */
    public int binaryRecFind(int[] data, int searchKey, int lowerBound, int upperBound) {
        if (data == null) {
            return -1;
        }
        int length = data.length;

        int curIn;

        // 取中间位置
        curIn = (lowerBound + upperBound) / 2;
        if (data[curIn] == searchKey) {
            return curIn;
        } else if (data[curIn] < searchKey) {
            //lowerBound = curIn + 1;
            return binaryRecFind(data, searchKey, curIn + 1, upperBound);
        } else if (data[curIn] > searchKey) {
            //upperBound = curIn - 1;
            return binaryRecFind(data, searchKey, lowerBound, curIn - 1);

        }

        return -1;
    }


    /**
     * 冒泡排序：第一次循环两两比较 大的数据交换位置 比较n-1次
     * 第二次循环从0-(n-2)排序
     * 。。。
     *
     * @param data 无序数组
     * @return
     */
    public void bubbleSort(int[] data) {
        if (data == null) {
            return;
        }

        for (int num : data) {
            System.out.print("排序前：" + num);
        }

        int length = data.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    swapDates(data, j + 1, j);
                }
            }
        }

        for (int num : data) {
            System.out.print("排序后：" + num);
        }

    }

    /**
     * 选择排序：
     * 第一次扫描从第一位开始找到最小的数与第一位交换
     * 第二次扫描从第二位开始找到最小的于第二位交换
     * 依次执行直到循环结束
     *
     * @param data 无序数组
     */
    public void selectSort(int[] data) {
        if (data == null) {
            return;
        }

        for (int num : data) {
            System.out.print("排序前：" + num);
        }

        int min; // 最小位置
        int length = data.length;
        for (int i = 0; i < length - 1; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
                swapDates(data, min, j);
            }
        }

        for (int num : data) {
            System.out.print("排序后：" + num);
        }
    }

    /**
     * 交换两个位置的数据
     *
     * @param data 原始数组
     * @param a    交换 a 位置
     * @param b    交换 b 位置
     */
    private void swapDates(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    /**
     * 插入排序：
     *
     * @param data 带排序数组
     */
    public void insertSort(int[] data) {
        if (data == null) {
            return;
        }

        for (int num : data) {
            System.out.print("排序前：" + num);
        }

        int in, out;
        for (out = 1; out < data.length; out++) {
            int temp = data[out];
            in = out;

            while (in > 0 && data[in - 1] >= temp) {
                data[in] = data[in - 1];
                --in;
            }
            data[in] = temp;
        }

        for (int num : data) {
            System.out.print("排序后：" + num);
        }
    }

    /**
     * 递归
     */
    private int triangle(int n) {
        if (n == 1) {
            return 1;
        } else {
            return (n + triangle(n - 1));
        }
    }

    /**
     * 归并排序
     *
     * @param arrayA
     * @param sizeA
     * @param arrayB
     * @param sizeB
     * @param arrayC
     */
    private void mergeSort(int[] arrayA, int sizeA, int[] arrayB, int sizeB, int[] arrayC) {
        int aDex = 0, bDex = 0, cDex = 0;

        while (aDex < sizeA && bDex < sizeB) { // neither array empty
            if (arrayA[aDex] < arrayB[bDex]) {
                arrayC[cDex++] = arrayA[aDex++];
            } else {
                arrayC[cDex++] = arrayB[bDex++];
            }
        }

        while (aDex < sizeA) { // arrayB isEmpty
            arrayC[cDex++] = arrayA[aDex++];
        }

        while (bDex < sizeB) {// arrayA isEmpty
            arrayC[cDex++] = arrayB[bDex++];
        }
    }


    private static void sum() {
        int sum = 0;
        long start = System.currentTimeMillis();
        System.out.println("循环求和开始：" + start);
        for (int i = 1; i < 101; i++) {
            sum = sum + i;
        }
        long end = System.currentTimeMillis();
        System.out.println("循环求和结束：" + sum + " 时间耗时：" + (end - start));
        // 高斯算法
        System.out.println("高斯算法求和开始：" + start);
        sum = (1 + 100) * 100 / 2;
        System.out.println("高斯算法求和：" + sum );
    }


}
