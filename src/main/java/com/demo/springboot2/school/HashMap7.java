package com.demo.springboot2.school;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class HashMap7<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 4434132848470666006L;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //默认初始化大小 16
    static final float DEFAULT_LOAD_FACTOR = 0.75f;     //负载因子0.75
    /**
     * 声明HashMap一个空数组。
     */
    static final Entry<?, ?>[] EMPTY_TABLE = {};
    /**
     * 开始时，HashMap是空数组
     */
    transient Entry<K, V>[] table = (Entry<K, V>[]) EMPTY_TABLE;
    transient int size;     //HashMap中元素的数量
    int threshold;          //判断是否需要调整HashMap的容量

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, HashMap7.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * set值返回前一个值
         */
        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    public HashMap7() {
    }

    @Override
    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        int hash = hash(key.hashCode());
        int var1 = hash % DEFAULT_INITIAL_CAPACITY;
        Node<K, V> var2 = (Node<K, V>) table[var1];
        if (var2.hash == hash) {

        }
        Node<K, V> node = new Node<K, V>(hash, key, value, var2);
        table[var1] = node;
        return super.put(key, value);
    }

    public void putVal(int hash) {

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
