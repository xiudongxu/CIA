package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.asmTree;



import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dongxu.xiu
 * @since 2019-05-16 上午11:18
 */
public class GenerateClasses {

    public static void main(String[] args) throws FileNotFoundException {
        ClassWriter cw = new ClassWriter(Opcodes.ASM5);
        ClassNode cn = gen();
        cn.accept(cw);
        File file = new File("ChildClass.class");
        FileOutputStream fout = new FileOutputStream(file);
        try {
            fout.write(cw.toByteArray());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ClassNode gen() {
        ClassNode classNode = new ClassNode();
        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT;
        classNode.name = "asm/core/ChildClass";
        classNode.superName = "java/lang/Object";
        classNode.interfaces.add("asm/core/ParentInter");
        classNode.fields.add(new FieldNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "zero", "I",
                null, new Integer(0)));
        classNode.methods.add(new MethodNode(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null));
        return classNode;

    }
}
