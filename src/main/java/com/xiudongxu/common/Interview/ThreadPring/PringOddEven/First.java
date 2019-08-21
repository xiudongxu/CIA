package com.xiudongxu.common.Interview.ThreadPring.PringOddEven;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * 两个线程交替打印奇数和偶数
 * 版本一：非通知模式
 * @author dongxu.xiu
 * @since 2019-05-22 下午7:02
 */
public class First {

    private static volatile AtomicInteger atomicInteger = new AtomicInteger();

    private static volatile LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                if((atomicInteger.get()&0x01) == 1){
                    System.out.println("thread1:"+atomicInteger.getAndIncrement());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                if((atomicInteger.get()&0x01) == 0){
                    System.out.println("thread2:"+atomicInteger.getAndIncrement());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
