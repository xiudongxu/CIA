package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.agent;

import jdk.internal.org.objectweb.asm.ClassVisitor;

import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AnalyzerAdapter;
import jdk.internal.org.objectweb.asm.commons.LocalVariablesSorter;
import org.objectweb.asm.Opcodes;

/**
 * desc：修改字节码的方法，给每个类添加一个字段UDASMCN 用来记录当前类的名字
 * 同时记录每个方法的名字和执行时间。
 *
 * @author dongxu.xiu
 * @since 2019-05-15 上午11:18
 */
public class TimeCountAdpter extends ClassVisitor implements Opcodes {

    private String owner;

    private boolean isInterface;

    private String filedName = "UDASMCN";

    //方法标识头
    private int acc = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

    private String methodName;

    private boolean isPresent = false;


    public TimeCountAdpter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    //前面的cr 调用accept之后 就调用visit 方法
    //所以在visit 方法中能拿到解析出来的各种值
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        owner = name;
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (!isInterface && mv != null && !name.equals("<init>") && !name.equals("<clinit>")) {
            methodName = name;
            AddTimerMethodAdapter at = new AddTimerMethodAdapter(mv);
            at.aa = new AnalyzerAdapter(owner, access, name, descriptor, at);
            at.lvs = new LocalVariablesSorter(access, descriptor, at.aa);
            return at.lvs;
        }
        return mv;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (name.equals(filedName)) {
            isPresent = true;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }


    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = cv.visitField(acc, filedName, "Ljava/lang/String;", null, owner);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }

    class AddTimerMethodAdapter extends MethodVisitor {

        private int time;

        private int maxStack;

        public LocalVariablesSorter lvs;

        public AnalyzerAdapter aa;

        public AddTimerMethodAdapter(MethodVisitor methodVisitor) {
            super(ASM6, methodVisitor);
        }

        //方法开始 是通过此方法的调用
        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime","()J",false);
            time = lvs.newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, time);
            maxStack = 4;
        }

        //
        @Override
        public void visitInsn(int opcode) {
            if (((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) && !isPresent) {
                //添加一个方法
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
                //加载一个time字段
                mv.visitVarInsn(LLOAD, time);
                mv.visitInsn(LSUB);
                //加载到操作数栈上
                mv.visitVarInsn(LSTORE, time);

                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

                mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                mv.visitInsn(DUP);

                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);

                mv.visitFieldInsn(GETSTATIC, owner, filedName, "Ljava/lang/String;");

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);

                mv.visitLdcInsn(" " + methodName + ":");

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);

                mv.visitVarInsn(LLOAD, time);

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder", false);

                mv.visitVarInsn(LLOAD, time);

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);

                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

                maxStack = Math.max(aa.stack.size() + 4, maxStack);
            }

            mv.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            super.visitMaxs(Math.max(maxStack, this.maxStack), maxLocals);
        }
    }
}
