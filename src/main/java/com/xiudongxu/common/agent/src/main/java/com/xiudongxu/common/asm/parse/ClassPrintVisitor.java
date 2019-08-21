package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.asm.parse;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author dongxu.xiu
 * @since 2019-05-15 下午5:38
 */
public class ClassPrintVisitor extends ClassVisitor {

    public ClassPrintVisitor() {
        super(Opcodes.ASM4);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        if (name.startsWith("is")) {
            System.out.println(" field name: " + name + desc);
        }
        return null;
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        if (name.startsWith("is")) {
            System.out.println(" start with is method: " + name + desc);
        }
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

}
