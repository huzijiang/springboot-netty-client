package com.demo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 *
 * 客户端初始化
 * 客户端与 服务器端 连接一旦创建，这个类中方法就会被回调，
 * 设置出站编码器 和  入站解码器，客户端与服务器端编码要保持一致。
 *
 * @Author  huzj
 * @Date 2020/9/9 17:25
 * @Version 1.0
 */
public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));

        socketChannel.pipeline().addLast(new NettyClientHandler());
    }

}
