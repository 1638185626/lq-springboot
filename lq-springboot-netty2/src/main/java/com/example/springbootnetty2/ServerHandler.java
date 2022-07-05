package com.example.springbootnetty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

/**
 * @className: ServerHandler
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/4
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("----------服务通道激活---------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // NIO 通信 （传输的数据是什么？    Buffer对象
            ByteBuf buf = (ByteBuf) msg;
            // 定义byte数组
            byte[] req = new byte[buf.readableBytes()];
            // 从缓存区获取数据到req
            buf.readBytes(req);
            // 读取到的数据转换为字符串
            String body = new String(req, StandardCharsets.UTF_8);
            System.out.printf("----------服务端读取到的数据---------%s",body);

            // 响应给客户端的数据
            ctx.writeAndFlush(Unpooled.copiedBuffer(body.getBytes()));
            // 添加
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("----------服务端数据读取完毕---------");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 服务端数据读取异常
        System.out.println("----------服务端数据读取异常---------");
        ctx.close();
    }
}
