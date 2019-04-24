package com.mobike.javaarchitecture.collection.tree;

import android.support.annotation.NonNull;

/**
 * 红-黑树的节点
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午9:35
 */
public class RBNode<T> implements Comparable<T> {

    private static final Boolean RED = true;
    boolean color; //颜色
    T key; //关键字(键值)
    RBNode<T> left; //左子节点
    RBNode<T> right; //右子节点
    RBNode<T> parent; //父节点

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.key = key;
        this.color = color;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    public String toString() {
        return "" + key + (this.color == RED ? "R" : "B");
    }

    @Override
    public int compareTo(@NonNull T o) {
        return 0;
    }
}
