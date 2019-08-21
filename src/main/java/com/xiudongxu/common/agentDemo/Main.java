package com.xiudongxu.common.agentDemo;

/**
 * @author dongxu.xiu
 * @since 2019-04-01 下午5:32
 */
public class Main {


    public static void main(String[] args) {
        hello();
    }

    private static void hello() {
        System.out.println(Main.class.getName());
        System.out.println("this is agent-demo output");
    }
}
