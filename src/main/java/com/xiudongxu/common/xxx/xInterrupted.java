package com.xiudongxu.common.xxx;

/**
 * @author dongxu.xiu
 * @since 2019-03-26 下午4:42
 */


public class xInterrupted {

    //在new 和 terminated 状态的线程对中断是屏蔽的。
    //Runnable 状态下的线程  设置了中断状态的话  也不会打断线程 只不过状态被设置了。
    //java 将线程中断的权利交给我们程序  java 给我们提供一个中断标志位，我们的程序可以通过判断
    //中断标志位 是否被设置来中断我们的程序 而不是系统的强制中断。
    //处于BLOCKED的线程  由于竞争某个对象的锁失败了  而被挂在该对象的*阻塞队列*上。那么此时发起中断操作不会对
    //该线程产生任何影响。
    //WAITING  是由于缺少某些条件而被挂起在某个对象的等待队列上 当这些线程遇到中断操作的时候  会抛出
    //InterruptedException  并清空中断标志位

    //抛出中断异常的方法 是可以进行中断的

    //wait sleep join 这三个方法都会产生InterruptedException
    //如果你要中断的话 就不要中这三个方法

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread();
        thread1.start();
        Thread thread2 = new MyThread();
        thread2.start();

        Thread.sleep(1000);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());
    }

    static class MyThread extends  Thread{
        public synchronized static void doSomething(){
            while(true){
                //do something
            }
        }
        @Override
        public void run() {
            doSomething();
        }

        public void isRunning(){

        }
    }



}
