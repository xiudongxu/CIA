package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author dongxu.xiu
 * @since 2019-04-01 下午5:11
 */


public class FirstAgent implements ClassFileTransformer {

    public final String injectedClassName = "com.xiudongxu.common.agent.Main";

    public final String methodName = "hello";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        if(injectedClassName.equals(className)){
            CtClass ctClass = null;
            try {
                //使用全称，用于获取字节码类
                ctClass = ClassPool.getDefault().get(className);
                CtMethod ctmethod = ctClass.getDeclaredMethod(methodName);
                ctmethod.insertBefore("System.out.println(\"before\");");

                ctmethod.insertAfter("System.out.println(\"after\");");
                return ctClass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
}
