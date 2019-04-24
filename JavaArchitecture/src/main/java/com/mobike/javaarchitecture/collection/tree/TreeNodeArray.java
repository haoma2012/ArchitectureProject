package com.mobike.javaarchitecture.collection.tree;

/**
 * 数组实现树
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午7:55
 */
public class TreeNodeArray<T> {

    private T mData;
    private int mParent; // root根节点

    public TreeNodeArray(T data, int parent) {
        mData = data;
        mParent = parent;
    }

    public int getmParent() {
        return mParent;
    }

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public void setmParent(int mParent) {
        this.mParent = mParent;
    }


}
