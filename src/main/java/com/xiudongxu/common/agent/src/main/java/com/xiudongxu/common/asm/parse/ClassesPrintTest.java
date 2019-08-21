package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.asm.parse;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * @author dongxu.xiu
 * @since 2019-05-15 下午5:43
 */
public class ClassesPrintTest {

    public static void main(String[] args) {
        try {
            ClassReader cr = new ClassReader("com.xiudongxu.common.asm.parse.Task");
            ClassPrintVisitor cp = new ClassPrintVisitor();
            cr.accept(cp,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
