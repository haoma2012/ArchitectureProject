package com.mobike.javaarchitecture.collection.tree;

/**
 * 二叉树
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午8:46
 */
public class BinaryTreeNode {

    /*
     * 一个二叉树包括 数据、左右孩子 三部分
     */
    private int mData;
    private BinaryTreeNode mLeftChild;
    private BinaryTreeNode mRightChild;

    public BinaryTreeNode() {

    }

    public BinaryTreeNode(int data, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        mData = data;
        mLeftChild = leftChild;
        mRightChild = rightChild;
    }


    public int getmData() {
        return mData;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }

    public BinaryTreeNode getmLeftChild() {
        return mLeftChild;
    }

    public void setmLeftChild(BinaryTreeNode mLeftChild) {
        this.mLeftChild = mLeftChild;
    }

    public BinaryTreeNode getmRightChild() {
        return mRightChild;
    }

    public void setmRightChild(BinaryTreeNode mRightChild) {
        this.mRightChild = mRightChild;
    }
}
