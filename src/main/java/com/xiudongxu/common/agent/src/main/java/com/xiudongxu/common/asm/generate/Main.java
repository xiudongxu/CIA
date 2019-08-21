package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.asm.generate;

import jdk.internal.org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * @author dongxu.xiu
 * @since 2019-05-15 下午5:51
 */
public class Main {


    public static void main(String[] args) {

        gen();
    }

    public static byte[] gen(){
        ClassWriter cw = new ClassWriter(0);

        //生成一个class的头部信息。 第一个参数的代表java版本、 第二个参数是类的修饰符、第三个是类名。第四个传一个类型参数。第五个是父类。第六个是接口。
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT ,
                "asm/core/ChildClass", null, "java/lang/Object", new String[]{"asm/core/ParentInter"});

        //生成属性
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "zero", "I", null, new Integer(0))
                .visitEnd();

        //生成方法
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null)
                .visitEnd();

        //结束整个创建过程
        cw.visitEnd();

        return cw.toByteArray();
    }
}
