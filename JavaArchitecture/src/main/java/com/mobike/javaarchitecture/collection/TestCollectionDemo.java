package com.mobike.javaarchitecture.collection;

import com.mobike.javaarchitecture.collection.sort.SortUtils;

import java.util.*;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午6:11
 */
public class TestCollectionDemo {

    public static void main(String args[]) {

        ArrayList<Hero> heroes = new ArrayList<>();
        Vector<Hero> heroVector = new Vector<>();
        LinkedList<Hero> linkedList = new LinkedList<>();


        // Map
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sb", "sb");

        System.out.println(stringMap.toString());

        HashSet<Integer> mNumSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            mNumSet.add(i + 1);

        }
        //Collections.synchronizedList(heroes);

        // 红黑树测试
        testRedTree();
        // 希尔排序
        SortUtils.shelSort();
        // 归并排序
        SortUtils.funMergeSort();


        shuffle();
        testSet();
        testPush();

    }

    private static void testRedTree() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("position", "0");

        System.out.println(map.toString());
    }

    private static void testSet() {
        HashSet<Integer> numberSet1 = new HashSet<Integer>();
        //HashSet中的数据不是按照插入顺序存放
        numberSet1.add(88);
        numberSet1.add(8);
        numberSet1.add(888);

        System.out.println(numberSet1);

        LinkedHashSet<Integer> numberSet2 = new LinkedHashSet<Integer>();
        //LinkedHashSet中的数据是按照插入顺序存放
        numberSet2.add(88);
        numberSet2.add(8);
        numberSet2.add(888);

        System.out.println(numberSet2);
        TreeSet<Integer> numberSet3 = new TreeSet<Integer>();
        //TreeSet 中的数据是进行了排序的
        numberSet3.add(88);
        numberSet3.add(8);
        numberSet3.add(888);

        System.out.println(numberSet3);

    }


    private static void shuffle() {
        //初始化集合numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        System.out.println("集合中的数据:");
        System.out.println(numbers);

        Collections.shuffle(numbers);

        System.out.println("混淆后集合中的数据:");
        System.out.println(numbers);

        Collections.sort(numbers);
        System.out.println("排序后集合中的数据:");
        System.out.println(numbers);

        Collections.swap(numbers, 0, 5);
        System.out.println("交换0和5下标的数据后，集合中的数据:");
        System.out.println(numbers);

        Collections.rotate(numbers, 2);
        System.out.println("把集合向右滚动2个单位，标的数据后，集合中的数据:");
        System.out.println(numbers);
    }

    private static void testPush() {
        //聚合方式
//        String name = heros
//                .stream()
//                .sorted((h1, h2) -> h1.hp > h2.hp ? -1 : 1)
//                .skip(2)
//                .map(h -> h.getName())
//                .findFirst()
//                .get();
//        System.out.println("通过聚合操作找出来的hp第三高的英雄名称是:" + name);

    }
}
