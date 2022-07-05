package com.example.springbootnetty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

/**
 * @className: ClientHandler
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/4
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("----------客户端通道激活---------");
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // NIO 通信 （传输的数据是什么？ ---------> Buffer 对象）
            ByteBuf buf = (ByteBuf) msg;
            //定义byte数组
            byte[] req = new byte[buf.readableBytes()];
            // 从缓冲区获取数据到 req
            buf.readBytes(req);
            //读取到的数据转换为字符串
            String body = new String(req, StandardCharsets.UTF_8);
            System.out.printf("----------客户端读取到数据---------%s",body);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放数据 （如果你读取数据后又写出去数据就不需要调用此方法，因为底层代码帮忙实现额释放数据）
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("----------客户端数据读取完成---------");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("----------客户端数据读取异常---------");
        ctx.close();
    }
}
