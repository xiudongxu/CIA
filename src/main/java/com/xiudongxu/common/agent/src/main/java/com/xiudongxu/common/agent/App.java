package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author dongxu.xiu
 * @since 2019-04-01 下午5:25
 */

public class App {

    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
        // 添加Transformer
        // 对字节码处理的方法的回调
        inst.addTransformer(new FirstAgent());
    }
}
