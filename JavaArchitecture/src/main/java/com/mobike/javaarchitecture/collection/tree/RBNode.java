package com.mobike.javaarchitecture.collection.tree;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * 红-黑树的节点
 * 1、节点是红色或黑色
 * 2、根是黑色
 * 3、所有叶子是黑色(叶子是NIL节点) [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
 * 4、每个红色节点的两个子节点都是黑色的(从每个叶子到跟的所有路径不能有两个连续的红色节点)
 * 5、从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点。
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/22 下午9:35
 */
public class RBNode<T> implements Comparable<T> {
    private static final Boolean RED = true;
    private static final boolean BLACK = true;
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


    /**
     * 红黑树
     * @param <K>
     * @param <V>
     */
    static final class TreeMapEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        TreeMapEntry<K, V> left;
        TreeMapEntry<K, V> right;
        TreeMapEntry<K, V> parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        TreeMapEntry(K key, V value, TreeMapEntry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Returns the key.
         *
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value associated with the key.
         *
         * @return the value associated with the key
         */
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value currently associated with the key with the given
         * value.
         *
         * @return the value associated with the key before this method was
         * called
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;

            return valEquals(key, e.getKey()) && valEquals(value, e.getValue());
        }

        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Test two values for equality.  Differs from o1.equals(o2) only in
     * that it copes with {@code null} o1 properly.
     */
    static final boolean valEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }
}
