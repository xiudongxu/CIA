package com.xiudongxu.common.Interview.ThreadPring.PringOddEven;


/**
 * @author dongxu.xiu
 * @since 2019-05-22 下午7:18
 */
public class Second {

    private static int count = 0;

    private static Object object = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                synchronized (object){
                    if((count & 0x01) == 0){
                        System.out.println("thread1:"+ count++);
                    }
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                synchronized (object){
                    if((count & 0x01) == 1){
                        System.out.println("thread2:"+ count++);
                    }
                }
            }
        }).start();
    }
}
