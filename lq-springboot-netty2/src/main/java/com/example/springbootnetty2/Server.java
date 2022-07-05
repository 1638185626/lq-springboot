package com.example.springbootnetty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @className: Server
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/4
 **/
public class Server {
    public static void main(String[] args) throws InterruptedException {
        // 用户接收客户端连接的线程工作组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 用于接收客户端连接读写请求操作的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 辅助类 帮我们创建netty服务
        ServerBootstrap b = new ServerBootstrap();
        // 绑定两个工作组
        b.group(bossGroup, workerGroup)
                // 设置NIO模式
                .channel(NioServerSocketChannel.class)
                // option 针对服务端配置； childOption 针对客户端连接通道配置
                // 设置tcp缓存区
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 设置发送数据的缓存大小
                .childOption(ChannelOption.SO_SNDBUF,32 * 1024)
                // 设置读取数据的缓存大小
                .childOption(ChannelOption.SO_RCVBUF,32 * 1024)
                // 设置保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                // 初始化绑定服务通道
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 为通道进行初始化：数据传输过来的时候会进行拦截和执行（可以有多个拦截器）
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture cf = b.bind(8765).sync();

        // 释放连接
        cf.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();

    }
}
