package com.xiudongxu.common.DesignPattern;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author dongxu.xiu
 * @since 2019-08-08 下午2:39
 */
public class ProducerConsumer {

    private static volatile boolean isRunning = false;

    public static void main(String[] args) {


    }

    static class Producer implements Runnable {

        private BlockingQueue<Data> queue;//内存缓冲区

        public Producer(BlockingQueue<Data> queue) {
            this.queue = queue;
        }

        //开始运行
        @Override
        public void run() {
            while (isRunning) {
                queue.offer(produceOneData());
            }
        }

        public Data produceOneData() {
            return new Data();
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<Data> queue;//内存缓冲区

        //开始运行
        @Override
        public void run() {
            try {
                while (isRunning) {
                    Data take = queue.take();
                    System.out.println(take.data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    static class Data {
        private int data;

        private Random r = new Random();

        public Data() {
            this.data = r.nextInt();
        }
    }
}

