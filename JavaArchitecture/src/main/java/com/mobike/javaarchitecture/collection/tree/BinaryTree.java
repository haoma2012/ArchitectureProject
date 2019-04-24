package com.mobike.javaarchitecture.collection.tree;

/**
 * 二叉树
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午8:49
 */
public class BinaryTree {

    private BinaryTreeNode mRoot; // 根节点

    public BinaryTree() {
    }

    public BinaryTree(BinaryTreeNode root) {
        mRoot = root;
    }

    public BinaryTreeNode getRoot() {
        return mRoot;
    }

    public void setRoot(BinaryTreeNode root) {
        mRoot = root;
    }


    /**
     * 添加左子树
     */
    public void insertAsLeftChild(BinaryTreeNode child) {
        checkTreeEmpty();
        mRoot.setmLeftChild(child);
    }

    /**
     * 添加右子树
     */
    public void insertAsRightChild(BinaryTreeNode child) {
        checkTreeEmpty();
        mRoot.setmRightChild(child);
    }

    /**
     * 删除节点元素
     *
     * @param node BinaryTreeNode
     */
    public void deleteNode(BinaryTreeNode node) {
        checkTreeEmpty();
        if (node == null) {  //递归出口
            return;
        }
        deleteNode(node.getmLeftChild());
        deleteNode(node.getmRightChild());
        node = null;
    }

    public void clear() {
        if (mRoot != null) {
            deleteNode(mRoot);
        }
    }

    /**
     * 获取树的高度 ，特殊的获得节点高度
     *
     * @return
     */
    public int getTreeHeight() {
        return getHeight(mRoot);
    }

    /**
     * 获得指定节点的度
     *
     * @param node
     * @return
     */
    public int getHeight(BinaryTreeNode node) {
        if (node == null) {      //递归出口
            return 0;
        }
        int leftChildHeight = getHeight(node.getmLeftChild());
        int rightChildHeight = getHeight(node.getmRightChild());

        int max = Math.max(leftChildHeight, rightChildHeight);

        return max + 1; //加上自己本身
    }


    private void checkTreeEmpty() {
        if (mRoot == null) {
            throw new IllegalStateException("Can't insert to a null tree! Did you forget set value for root?");
        }
    }


    /**
     * 先序遍历
     *
     * @param node
     */
    public void iterateFirstOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        operate(node);
        iterateFirstOrder(node.getmLeftChild());
        iterateFirstOrder(node.getmRightChild());
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void iterateMidOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        iterateMidOrder(node.getmLeftChild());
        operate(node);
        iterateMidOrder(node.getmRightChild());
    }


    /**
     * 后序遍历
     *
     * @param node
     */
    public void iterateLastOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        iterateFirstOrder(node.getmLeftChild());
        iterateFirstOrder(node.getmRightChild());
        operate(node);
    }


    /**
     * 模拟操作
     *
     * @param node
     */
    public void operate(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getmData());
    }

    /**
     * 二叉排序树查找
     *
     * @param data 待查找树
     * @return BinaryTreeNode
     */
    public BinaryTreeNode search(int data) {

        return search(mRoot, data);
    }

    /**
     * 在指定二叉排序树中查找数据
     *
     * @param node
     * @param data
     * @return
     */
    public BinaryTreeNode search(BinaryTreeNode node, int data) {
        if (node == null || node.getmData() == data) {    //节点为空或者相等，直接返回该节点
            return node;
        }
        if (data < node.getmData()) {    //比节点小，就从左子树里递归查找
            return search(node.getmLeftChild(), data);
        } else {        //否则从右子树
            return search(node.getmRightChild(), data);
        }
    }

    /**
     * 插入到整个树中
     *
     * @param data
     */
    public void insert(int data) {
        if (mRoot == null) {     //如果当前是空树，新建一个
            mRoot = new BinaryTreeNode();
            mRoot.setmData(data);
            return;
        }

        searchAndInsert(null, mRoot, data);     //根节点的父亲为 null

    }

    /**
     * 两步走：查找、插入
     *
     * @param parent 要绑定的父节点
     * @param node   当前比较节点
     * @param data   数据
     */
    private BinaryTreeNode searchAndInsert(BinaryTreeNode parent, BinaryTreeNode node, int data) {
        if (node == null) {  //当前比较节点为 空，说明之前没有这个数据，直接新建、插入
            node = new BinaryTreeNode();
            node.setmData(data);
            if (parent != null) {    //父节点不为空，绑定关系
                if (data < parent.getmData()) {
                    parent.setmLeftChild(node);
                } else {
                    parent.setmRightChild(node);
                }
            }
            return node;
        }
        //对比的节点不为空
        if (node.getmData() == data) {    //已经有了，不用插入了
            return node;
        } else if (data < node.getmData()) {   //比节点小，从左子树里查找、插入
            return searchAndInsert(node, node.getmLeftChild(), data);
        } else {
            return searchAndInsert(node, node.getmRightChild(), data);
        }
    }

    /**
     * 在整个树中 查找指定数据节点的父亲节点
     *
     * @param data
     * @return
     */
    public BinaryTreeNode searchParent(int data) {
        return searchParent(null, mRoot, data);
    }

    /**
     * 在指定节点下 查找指定数据节点的父亲节点
     *
     * @param parent 当前比较节点的父节点
     * @param node   当前比较的节点
     * @param data   查找的数据
     * @return
     */
    public BinaryTreeNode searchParent(BinaryTreeNode parent, BinaryTreeNode node, int data) {
        if (node == null) { //比较的节点为空返回空
            return null;
        }
        if (node.getmData() == data) {    //找到了目标节点，返回父节点
            return parent;
        } else if (data < node.getmData()) {   //数据比当前节点小，左子树中递归查找
            return searchParent(node, node.getmLeftChild(), data);
        } else {
            return searchParent(node, node.getmRightChild(), data);
        }
    }

    /**
     * 删除指定数据的节点
     *
     * @param data
     */
    public void delete(int data) {
        if (mRoot == null || mRoot.getmData() == data) {  //根节点为空或者要删除的就是根节点，直接删掉
            mRoot = null;
            return;
        }
        //在删除之前需要找到它的父亲
        BinaryTreeNode parent = searchParent(data);
        if (parent == null) {        //如果父节点为空，说明这个树是空树，没法删
            return;
        }

        //接下来该找要删除的节点了
        BinaryTreeNode deleteNode = search(parent, data);
        if (deleteNode == null) {    //树中找不到要删除的节点
            return;
        }
        //删除节点有 4 种情况
        //1.左右子树都为空，说明是叶子节点，直接删除
        if (deleteNode.getmLeftChild() == null && deleteNode.getmRightChild() == null) {
            //删除节点
            deleteNode = null;
            //重置父节点的孩子状态，告诉他你以后没有这个儿子了
            if (parent.getmLeftChild() != null && parent.getmLeftChild().getmData() == data) {
                parent.setmLeftChild(null);
            } else {
                parent.setmRightChild(null);
            }
            return;
        } else if (deleteNode.getmLeftChild() != null && deleteNode.getmRightChild() == null) {
            //2.要删除的节点只有左子树，左子树要继承位置
            if (parent.getmLeftChild() != null && parent.getmLeftChild().getmData() == data) {
                parent.setmLeftChild(deleteNode.getmLeftChild());
            } else {
                parent.setmRightChild(deleteNode.getmRightChild());
            }
            deleteNode = null;
            return;
        } else if (deleteNode.getmRightChild() != null && deleteNode.getmRightChild() == null) {
            //3.要删除的节点只有右子树，右子树要继承位置
            if (parent.getmLeftChild() != null && parent.getmLeftChild().getmData() == data) {
                parent.setmLeftChild(deleteNode.getmRightChild());
            } else {
                parent.setmRightChild(deleteNode.getmRightChild());
            }

            deleteNode = null;
        } else {
            //4.要删除的节点儿女双全，既有左子树又有右子树，需要选一个合适的节点继承，这里使用右子树中最左节点
            BinaryTreeNode copyOfDeleteNode = deleteNode;   //要删除节点的副本，指向继承节点的父节点
            BinaryTreeNode heresNode = deleteNode.getmRightChild(); //要继承位置的节点，初始为要删除节点的右子树的树根
            //右子树没有左孩子了，他就是最小的，直接上位
            if (heresNode.getmLeftChild() == null) {
                //上位后，兄弟变成了孩子
                heresNode.setmLeftChild(deleteNode.getmLeftChild());
            } else {
                //右子树有左孩子，循环找到最左的，即最小的
                while (heresNode.getmLeftChild() != null) {
                    copyOfDeleteNode = heresNode;       //copyOfDeleteNode 指向继承节点的父节点
                    heresNode = heresNode.getmLeftChild();
                }
                //找到了继承节点，继承节点的右子树（如果有的话）要上移一位
                copyOfDeleteNode.setmLeftChild(heresNode.getmRightChild());
                //继承节点先继承家业，把自己的左右孩子变成要删除节点的孩子
                heresNode.setmLeftChild(deleteNode.getmLeftChild());
                heresNode.setmRightChild(deleteNode.getmRightChild());
            }
            //最后就是确认位置，让要删除节点的父节点认识新儿子
            if (parent.getmLeftChild() != null && parent.getmLeftChild().getmData() == data) {
                parent.setmLeftChild(heresNode);
            } else {
                parent.setmRightChild(heresNode);
            }
        }
    }

}
