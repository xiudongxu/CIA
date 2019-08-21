package com.xiudongxu.common.Interview.ThreadPring.PringABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongxu.xiu
 * @since 2019-05-17 下午3:52
 */
public class PrintABC_Condition {

    private static Lock lock = new ReentrantLock();

    private static Condition A = lock.newCondition();

    private static Condition B = lock.newCondition();

    private static Condition C = lock.newCondition();

    private static int count = 0;

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while(count % 3 != 0){
                        A.await();
                    }
                    System.out.print("A");
                    count++;
                    B.signal();//唤醒B线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while(count % 3 != 1){
                        B.await();
                    }
                    System.out.print("B");
                    count++;
                    C.signal();//唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while(count % 3 != 2){
                        C.await();
                    }
                    System.out.print("C");
                    count++;
                    A.signal();//唤醒A线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadC()).start();
    }
}
