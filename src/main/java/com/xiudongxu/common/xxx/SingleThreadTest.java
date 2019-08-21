package com.xiudongxu.common.xxx;

import org.codehaus.janino.util.TeeReader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongxu.xiu
 * @since 2019-05-16 下午7:40
 */
public class SingleThreadTest implements Runnable {

    public static ExecutorService es = Executors.newSingleThreadExecutor();

    @Override
    public void run() {
        int i = 0;
        int temp = 1;
        while (true) {
            try {
                i++;
                System.out.println("i:" + i + "temp:" + temp);
                Thread.sleep(500);
                if (i == 10) {
                    int a = i / 0;
                }
            } catch (InterruptedException e) {
                System.out.println("1");
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int count = 0;
        SingleThreadTest singleThreadTest = new SingleThreadTest();

        es.execute(() -> {

                    if (count == 100) {
                        throw new IllegalStateException("handler exception");
                    }
                    System.out.println(Thread.currentThread()+" - testAsyncRunner1 run ... "+count);
                }
        );

    }
}
