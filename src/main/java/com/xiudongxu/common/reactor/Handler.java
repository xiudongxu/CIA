package com.xiudongxu.common.reactor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dongxu.xiu
 * @since 2019-03-15 下午7:29
 */
public class Handler implements Runnable {


    private volatile static Selector selector;

    private SelectionKey key;

    private final SocketChannel channel;

    private volatile ByteBuffer input = ByteBuffer.allocate(1024);
    private volatile ByteBuffer output = ByteBuffer.allocate(1024);

    public Handler(SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        selector = Selector.open();
        key = channel.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void run() {
        try {
            while(selector.isOpen() && channel.isOpen()){
                //等待客户端事件发生
                Set<SelectionKey> keys = select();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    //如果当前是读事件 则读
                    if(key.isReadable()){
                        read(key);
                    }else if(key.isWritable()){
                        write(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取客户端发送的数据
    private void read(SelectionKey key) throws IOException {
        channel.read(input);
        if(input.position() == 0){
            return ;
        }

        input.flip();
        //对读取的数据进行业务处理
        process();
        input.clear();
        //读取完成后监听写事件
        key.interestOps(SelectionKey.OP_WRITE);
    }



    private void write(SelectionKey key) throws IOException {
        output.flip();
        if(channel.isOpen()){
            //当有写事件时 将业务处理的结果写入到客户端channel中
            channel.write(output);
            key.channel();
            channel.close();
            output.clear();
        }
    }

    private void process() throws UnsupportedEncodingException {
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("receive message fron client " + message);
        //直接就给回复了
        output.put("hello client".getBytes());
    }
    //解决JDK的一个BUG 这个BUG的意思是 selector被意外的触发，但是并没有事件到来

    private Set<SelectionKey> select() throws IOException {
        selector.select();

        Set<SelectionKey> keys = selector.selectedKeys();

        if(keys.isEmpty()){
            int interestOps = key.interestOps();
            selector = Selector.open();
            key = channel.register(selector, interestOps);
            return select();
        }
        return keys;
    }
}
