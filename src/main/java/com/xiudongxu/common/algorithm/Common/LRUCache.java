package com.xiudongxu.common.algorithm.Common;

import com.google.common.collect.Maps;
import com.xiudongxu.common.algorithm.Sort.HeapSort;

import java.util.HashMap;

/**
 * @author dongxu.xiu
 * @since 2019-08-09 下午6:10
 */
public class LRUCache<K, V> {

    private final int MAX_CACHE_SIZE;
    private HashMap<K, Entry<K, V>> hashMap;
    private Entry head;
    private Entry tail;



    public LRUCache(int MAX_CACHE_SIZE) {
        this.MAX_CACHE_SIZE = MAX_CACHE_SIZE;
        hashMap = Maps.newHashMap();
    }

    public void put(K key, V value){
        //如果头是空的
        if(head == null){
            head = new Entry(key, value, null, null);
            tail = head;
            hashMap.put(key,head);
            return;
        }
        //如果已经包含数据了，需要更新
        if(hashMap.containsKey(key)){
            hashMap.get(key).value = value;
            moveToHead(key);
            return;
        }


    }

    private void moveToHead(K key) {
        Entry<K, V> target = hashMap.get(key);

        //头部元素
        if(target.pre == null){
            return ;
        }
        //尾部元素
        if(target.next == null){
            tail = target.pre;
            tail.next = null;
        }
    }


    private Entry<K, V> getEntry(K key){
        return hashMap.get(key);
    }

    class Entry<K, V>{
        public Entry pre;
        public Entry next;
        public K key;
        public V value;

        public Entry(K key, V value, Entry pre, Entry next) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }
}
