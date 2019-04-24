package com.mobike.javaarchitecture.collection.tree;

/**
 * 链表实现树
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午7:58
 */
public class TreeNodebyLinkList<T> {

    private T mData;
    private TreeNodebyLinkList parent;
    private TreeNodebyLinkList kid;

    public TreeNodebyLinkList(T data,TreeNodebyLinkList parent) {
        mData =data;
        this.parent = parent;
    }

    public T getmData() {
        return mData;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public TreeNodebyLinkList getKid() {
        return kid;
    }

    public void setKid(TreeNodebyLinkList kid) {
        this.kid = kid;
    }

    public TreeNodebyLinkList getParent() {
        return parent;
    }

    public void setParent(TreeNodebyLinkList parent) {
        this.parent = parent;
    }
}
