package com.xiudongxu.common.CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dongxu.xiu
 * @since 2019-05-17 下午6:27
 */
public class ConcurrentStack<E> {

    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public void push(E item){
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;

        while (true){
            oldHead = top.get();
            newHead.next = oldHead;
            if(top.compareAndSet(oldHead, newHead)){
                return ;
            }
        }

    }

    public E pop(){
        while(true){
            Node<E> oldHead = top.get();
            if(oldHead == null){
                return null;
            }
            Node<E> newHead = oldHead.next;
            if(top.compareAndSet(oldHead, newHead)){
                return oldHead.item;
            }
        }
    }
    private static class Node<E>{
        public final E item;
        public Node<E> next;
        public Node(E item) {
            this.item = item;
        }
    }
}
