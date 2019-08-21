package com.xiudongxu.common.Interview.ThreadPring.PringABC;

/**
 * @author dongxu.xiu
 * @since 2019-05-17 下午4:24
 */
public class ThreeThreadPring1 {

    private static int count = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final Object lock3 = new Object();

    public static void main(String[] args) {
        multiTurning();
    }

    public static void multiTurning(){
        new Thread(new MultiTurningRunner(lock2,lock1,"线程1:1")).start();
        new Thread(new MultiTurningRunner(lock3,lock2,"线程2:2")).start();
        new Thread(new MultiTurningRunner(lock1,lock3,"线程3:3")).start();
    }

    static class MultiTurningRunner implements Runnable{

        private final Object nextLock;
        private final Object currentLock;
        private final String content ;

        public MultiTurningRunner(Object nextLock, Object currentLock, String content) {
            this.nextLock = nextLock;
            this.currentLock = currentLock;
            this.content = content;
        }

        @Override
        public void run() {
            while(count <= 100){
                synchronized (nextLock){
                    synchronized (currentLock){
                        System.out.println(content);
                        count++;
                        currentLock.notifyAll();
                    }
                    try {
                        if(count <= 100){
                            nextLock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
