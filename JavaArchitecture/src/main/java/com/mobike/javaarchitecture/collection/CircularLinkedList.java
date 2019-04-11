package com.mobike.javaarchitecture.collection;

/**
 * 循环链表
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/12 下午9:26
 */
public class CircularLinkedList {

    class Element {
        public Object value = null;
        private Element next = null;
    }

    private Element header = null;//头结点


    public void initList() {
        header = new Element();
        header.value = null;
        header.next = header;
    }

    /**
     * 插入链表
     */
    void insertList(Object o) {
        if (o == null) {
            return;
        }
        Element e = new Element();
        e.value = o;
        if (header.next == header)//第一次插入元素
        {
            header.next = e;
            e.next = header;
        } else//不是第一次插入元素
        {
            //temp引用在栈中，temp和header引用都指向堆中的initList()中new的Element对象
            Element temp = header;
            while (temp.next != header)//寻找最后一个元素
            {
                temp = temp.next;
            }
            temp.next = e;
            e.next = header;//新插入的最后一个节点指向头结点
        }
    }

    /**
     * 删除链表中第i个元素
     */
    void deletelist(Object o) {
        Element temp = header;
        while (temp.next != header) {
            //判断temp当前指向的结点的下一个结点是否是要删除的结点
            if (temp.next.value.equals(o)) {
                temp.next = temp.next.next;//删除结点
            } else {
                temp = temp.next;//temp“指针”后移
            }
        }
    }


    /**
     * 链表长度
     */
    int size() {
        Element temp = header;
        int size = 0;
        while (temp.next != header) {
            size++;
            temp = temp.next;
        }
        return size;
    }

}
