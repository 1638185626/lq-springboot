package com.example.springbootnetty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @className: Client
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/4
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 线程工作组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 辅助类 帮我们创建 netty服务
        Bootstrap b = new Bootstrap();
        // 绑定两个工作组
        b.group(workerGroup)
                .channel(NioSocketChannel.class)
                // 初始化服务通道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                })
        ;
        ChannelFuture cf = b.connect("127.0.0.1",8765).syncUninterruptibly();
        for (int i = 0; i < 100; i++) {
            cf.channel().writeAndFlush(Unpooled.copiedBuffer("nety client request data".getBytes(StandardCharsets.UTF_8)));
        }
        // 释放连接
        cf.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }
}
