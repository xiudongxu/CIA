package com.xiudongxu.common.Interview.ThreadPring.PringABC;


/**
 * @author dongxu.xiu
 * @since 2019-05-17 下午3:34
 */
public class PrintABC {

    public static class ThreadPrinter implements Runnable{

        private String name;
        private Object prev;
        private Object self;

        public ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }


        @Override
        public void run() {
            int count = 10;
            while(count > 0){
                synchronized (prev){

                    synchronized (self){
                        System.out.println(name);
                        count--;
                        self.notifyAll();
                    }

                    try {
                        if(count == 0){
                            prev.notifyAll();
                        }else{
                            prev.wait();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread thread1 = new Thread(new ThreadPrinter("A", c, a));
        Thread thread2 = new Thread(new ThreadPrinter("B", a, b));
        Thread thread3 = new Thread(new ThreadPrinter("C", b, c));

        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        thread3.start();
        Thread.sleep(100);

    }
}
