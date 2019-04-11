package com.mobike.javaarchitecture.collection;

/**
 * 链表 节点类
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/12 下午8:25
 */
public class Node {

    // 双向链表 指针域指向前一个节点
    public Node prev;

    // 数据域
    public int data;
    // 指针域 指向下一个节点
    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    /**
     * 插入节点
     *
     * @param head  头指针
     * @param index 要插入的位置
     * @param value 要插入的值
     */
    public static void insertNode(Node head, int index, int value) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("插入位置不合法。");
            return;
        }

        //临时节点，从头节点开始
        Node temp = head;

        //记录遍历的当前位置
        int currentPos = 0;

        //初始化要插入的节点
        Node insertNode = new Node(value);
        while (temp.next != null) {
            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {

                //temp表示的是上一个节点
                //将原本由上一个节点的指向交由插入的节点来指向
                insertNode.next = temp.next;

                //将上一个节点的指针域指向要插入的节点
                temp.next = insertNode;

                return;

            }
            currentPos++;
            temp = temp.next;
        }

    }


    /**
     * 遍历链表
     *
     * @param head 头节点
     */
    public static void traverse(Node head) {
        Node temp = head.next;
        while (temp != null) {
            System.out.print("获取到当前节点的数据：" + temp.data);
            temp = temp.next;
        }
    }

    /**
     * 获取链表的长度
     *
     * @param head 头指针
     */
    public static int linkListLength(Node head) {

        int length = 0;

        //临时节点，从首节点开始
        Node temp = head.next;

        // 找到尾节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

}
