package com.xiudongxu.common.agent.src.main.java.com.xiudongxu.common.agent;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author dongxu.xiu
 * @since 2019-05-15 上午11:06
 */
public class LogTransformer implements ClassFileTransformer {



    //asm 是Java中比较流行的用来读写字节码的类库，用来基于字节码层面对代码进行分析和转换。比如CGLIB用它来实现动态代理
    //返回字节码
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            //字节码 读取和分析引擎  将class 解析成byte数组
            //通过accept方法按顺序调用绑定对象(继承了ClassVisitor的实例 就是下面的TimeCountAdpter)的方法
            //解析类的方法、属性、注解、父类和接口信息 内部类等等。
            ClassReader cr = new ClassReader(className);

            //实现了ClassVisitor接口，用于拼接字节码。
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            //
            TimeCountAdpter timeCountAdpter = new TimeCountAdpter(cw);

            //解析字节码中常量池之后的所有元素。

            //紧接着常量池的2个字节是该类的access标签：ACC_PUBLIC、ACC_FINAL等。
            //之后2个字节为当前类名在常量池CONSTANT_Utf8_Info类型的索引。
            //之后2个字节为其父类名在常量池CONSTANT_Utf8_Info类型的索引。(索引值0表示父类为null，即直接继承自Object类)
            //再之后为其实现的接口数长度和对应各个接口名在常量池中CONSTANT_Utf8_Info类型的索引值
            //暂时先跳过Field和Method定义信息，解析类的attribute表，它用两个字节表达attribute数组的长度，每个attribute项中最前面2个字节是attribute名称：
            //SourceFile（读取sourceFile值）、InnerClasses（暂时纪录起始索引）、
            //EnclosingMethod（纪录当前匿名类、本地类包含者类名以及包含者的方法名和描述符）、Signature（类的签名信息，用于范型）、
            //RuntimeVisibleAnnotations（暂时纪录起始索引）、Deprecated（表识属性）、Synthetic（标识属性）、SourceDebugExtension
            //(为调试器提供的自定义扩展信息，读取成一个字符串)、RuntimeInvisibleAnnotations（暂时纪录起始索引），
            //对其他不识别的属性，纪录成Attribute链，如果attribute名称符合在accept中attribute数组中指定的attribute名，则替换传入的attribute数组对应的项；
            cr.accept(timeCountAdpter,ClassReader.EXPAND_FRAMES);

            //返回byte数组形式构建编译后的class 可以视为一个事件的消费者。
            return cw.toByteArray();

        } catch (IOException e) {

        }
        return null;
    }
}
