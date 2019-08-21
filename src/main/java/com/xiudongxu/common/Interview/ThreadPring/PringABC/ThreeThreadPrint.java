package com.xiudongxu.common.Interview.ThreadPring.PringABC;

/**
 *
 *
 * desc：三个线程交替打印  123 456 789
 *
 * ex:
 * a->1
 * b->2
 * c->3
 *
 * a->4
 * b->5
 * c->6
 * @author dongxu.xiu
 * @since 2019-05-17 下午1:16
 */



public class ThreeThreadPrint {


    public static void main(String[] args) {
        Num num = new Num(1,10);
        Thread thread1 = new Thread(new PrintOne(num));
        Thread thread2 = new Thread(new PrintTwo(num));
        Thread thread3 = new Thread(new PrintThree(num));
        thread1.start();
        thread2.start();
        thread3.start();
    }
    //打印线程1
    static class PrintOne implements Runnable{
        private Num num;
        public PrintOne(Num num) {
            this.num = num;
        }
        @Override
        public void run() {
            while(true){
                try {
                    if(!num.printOne()){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + "exit!");
        }
    }
    //打印线程2
    static class PrintTwo implements Runnable{
        private Num num;
        public PrintTwo(Num num) {
            this.num = num;
        }
        @Override
        public void run() {
            while(true){
                try {
                    if(!num.printTwo()){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + "exit!");
        }
    }

    //打印线程3
    static class PrintThree implements Runnable{
        private Num num;
        public PrintThree(Num num) {
            this.num = num;
        }
        @Override
        public void run() {
            while(true){
                try {
                    if(!num.printThree()){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + "exit!");
        }
    }

    //数字类
    static class Num{
        private int startNum;
        private int endNum;
        public Num(int startNum,int endNum){
            this.startNum = startNum;
            this.endNum = endNum;
        }
        public synchronized boolean printOne() throws InterruptedException {
            while(startNum % 3 != 1){
                if(startNum > endNum){
                    this.notifyAll();
                    return false;
                }
                this.wait();
            }
            if(startNum > endNum){
                this.notifyAll();
                return false;
            }
            //将其打印出来
            System.out.println(Thread.currentThread().getName() + "->" + (startNum++));
            Thread.sleep(1000);
            this.notifyAll();
            return true;
        }
        public synchronized boolean printTwo() throws InterruptedException {
            while(startNum % 3 != 2){
                if(startNum > endNum){
                    this.notifyAll();
                    return false;
                }
                this.wait();
            }
            if(startNum > endNum){
                this.notifyAll();
                return false;
            }
            //将其打印出来
            System.out.println(Thread.currentThread().getName() + "->" + (startNum++));
            Thread.sleep(1000);
            this.notifyAll();
            return true;
        }
        public synchronized boolean printThree() throws InterruptedException {
            while(startNum % 3 != 0){
                if(startNum > endNum){
                    this.notifyAll();
                    return false;
                }
                this.wait();
            }
            if(startNum >= endNum){
                this.notifyAll();
                return false;
            }
            //将其打印出来
            System.out.println(Thread.currentThread().getName() + "->" + (startNum++));
            Thread.sleep(1000);
            this.notifyAll();
            return true;
        }
    }
}
