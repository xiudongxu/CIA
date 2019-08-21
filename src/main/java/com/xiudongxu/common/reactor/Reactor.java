package com.xiudongxu.common.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dongxu.xiu
 * @since 2019-03-15 下午5:47
 */
public class Reactor implements Runnable {

    //主要内容
    //http://www.cnblogs.com/winner-0715/p/8733787.html
    //https://mp.weixin.qq.com/s?__biz=MzAxMjY5NDU2Ng==&mid=2651853150&idx=1&sn=606f9757f04da6b1732fe099d4abf3d6&chksm=80495417b73edd01810b9420eb25a5b498991c2474bc08ee9ad6dbb3157464e756c93d3489a5&mpshare=1&scene=1&srcid=03144xTw57b1yo2ldfyLi5g8#rd
    //基本内容将的很全面
    //https://blog.csdn.net/robinjwong/article/details/41792623

    /**
     * Selector(选择器)是Java NIO中能够检测一到多个NIO通道，
     * 并能够知晓通道是否为诸如读写事件做好准备的组件。
     * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
     */
    private final Selector selector;

    /**
     * 为了将Channel和Selector配合使用，必须将channel注册到selector上。
     * 通过SelectableChannel。register()方法来实现。
     */
    private final ServerSocketChannel serverSocket;

    /**
     * 注：与Selector一起使用 Channel必须处于非阻塞模式下
     *  所以 FileChannel 与 Selector 不能一起使用
     */


    /**
     * selectionKey 该对象是用于跟踪这些被注册事件的句柄
     *
     * all-keys集合 —— 当前所有向Selector注册的SelectionKey的集合，Selector的keys()方法返回该集合
     * selected-keys集合 —— 相关事件已经被Selector捕获的SelectionKey的集合，Selector的selectedKeys()方法返回该集合
     * cancelled-keys集合 —— 已经被取消的SelectionKey的集合，Selector没有提供访问这种集合的方法
     */


    public Reactor(int port) throws IOException {
        //创建服务端的ServerSocketChannel
        serverSocket = ServerSocketChannel.open();
        //设置为非阻塞模式
        serverSocket.configureBlocking(false);
        //创建一个Selector多路复用器
        selector = Selector.open();
        //这个selector 注册到 serverSocket上  事件是 accept
        SelectionKey key = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        //绑定服务端端口
        serverSocket.bind(new InetSocketAddress(port));
        //为服务端Channel绑定一个Acceptor
        key.attach(new Acceptor(serverSocket));
    }

    public void run() {
        try {
            while(!Thread.interrupted()){
                //服务端使用一个线程不断等待客户端的连接到达
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                while(iterator.hasNext()){
                    //监听到连接事件之后将其分发给Acceptor
                    dispatch(iterator.next());
                    iterator.remove();
                }
                selector.selectNow();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable attachment = (Runnable) key.attachment();
        attachment.run();
    }
}
