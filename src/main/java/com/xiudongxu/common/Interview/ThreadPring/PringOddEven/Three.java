package com.xiudongxu.common.Interview.ThreadPring.PringOddEven;


/**
 * @author dongxu.xiu
 * @since 2019-05-22 下午7:18
 */
public class Three {

    private static int count = 0;

    private static Object object = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (object) {
                    System.out.println("偶数:" + count++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.notifyAll();
                    if(count <= 100){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (object) {
                    System.out.println("奇数:"+count++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.notifyAll();
                    if(count <= 100){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
