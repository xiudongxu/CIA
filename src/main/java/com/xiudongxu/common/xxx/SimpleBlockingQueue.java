package com.xiudongxu.common.xxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongxu.xiu
 * @since 2019-03-25 下午4:55
 */
public class SimpleBlockingQueue {

    //队列的容器  动态数组
    private List<Integer> container = new ArrayList<>();

    private int maxCount = 100;

    //用于记录容器中元素的个数
    private int count;

    //可重入锁
    private Lock lock = new ReentrantLock();

    //加入一个就要通知一下
    private final Condition addCondition = lock.newCondition();

    private final Condition takeCondition = lock.newCondition();

    public void add(Integer item) throws InterruptedException {
        if(item == null){
            throw new RuntimeException("不可为null");
        }
        //加锁
        lock.lockInterruptibly();
        //这里正常是要判断是不是有界的

        container.add(item);
        count++;
        //添加成功之后发出唤醒通知
        takeCondition.signal();
        lock.unlock();
    }

    public Integer get() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            //await 要和 while 搭配
            while(count == 0){
                takeCondition.await();
            }
            count--;
            addCondition.signalAll();
            return container.remove(0);
        } finally {
            lock.unlock();
        }
    }






}
