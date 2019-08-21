package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.asm.parse;

/**
 * @author dongxu.xiu
 * @since 2019-05-15 下午5:37
 */
public class Task {

    private int isTask = 0;

    private long tell = 0;

    public void isTask() {
        System.out.println("call isTask");
    }

    public void tellMe() {
        System.out.println("call tellMe");
    }
}
