package com.mobike.javaarchitecture.collection;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 双向链表
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/12 下午8:58
 */
public class DoubleLink<T> implements Iterable<T> {

    public class Note{
        public Note prev;
        public T data;
        public Note next;
    }

    Note head; // 头节点
    Note rear; // 尾节点

    // 增加节点
    public void add(T data) {
        Note note = new Note();
        note.data = data;

        if (head == null) { //  等同head == null && rear == null,head没有那rear肯定没有
            head = note;
            rear = note;
        } else {
            note.prev = null;
            note.next = head;
            head.prev = note;
            head = note;
        }
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
