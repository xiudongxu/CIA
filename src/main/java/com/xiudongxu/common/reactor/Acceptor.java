package com.xiudongxu.common.reactor;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongxu.xiu
 * @since 2019-03-15 下午5:59
 */
public class Acceptor implements Runnable {


    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    private final ServerSocketChannel serverSocket;

    public Acceptor(ServerSocketChannel serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            //获取客户端连接
            SocketChannel channel = serverSocket.accept();
            if(channel != null){
            //将客户端连接交给线程池处理
                executor.execute(new Handler(channel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
