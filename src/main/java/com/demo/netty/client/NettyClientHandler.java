package com.demo.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端处理类
 * @Author huzj
 * @Date 2020/9/9 17:37
 * @Version 1.0
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 用于计算 多少个模拟的客户端 接入
     */
    private static final ConcurrentHashMap<ChannelId, ChannelHandlerContext> CLIENT_MAP=new ConcurrentHashMap<>();

    /**
     * 活动时 触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CLIENT_MAP.put(ctx.channel().id(),ctx);
        log.info("客户端处理类  活动中");
    }

    /**
     * 有服务端端终止连接服务器会触发此函数
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
        log.info("服务端 终止了 服务");
    }

    /**
     *接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       log.info("服务端回写数据："+msg);
    }

    /**
     * 服务端  发生异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       log.info("服务端发生异常："+cause.getMessage());
       ctx.close();
    }

    public void channelWrite(ChannelId channelId,String msg){

        ChannelHandlerContext ctx=CLIENT_MAP.get(channelId);

        if(ctx ==null){
            log.info("通道："+channelId+"不存在.");
            return;
        }
        ctx.write(msg+"时间"+ LocalDateTime.now());

        ctx.flush();
    }



}
