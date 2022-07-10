package com.demo.netty.client;

/**
 * @Author huzj
 * @Date 2020/9/9 18:50
 * @Version 1.0
 */
public class NettyClientTest {

    public static void main(String[] args) {
        for (int i=1;i<=1;i++){
            new Thread(new NettyClient("thread"+"--"+i)).start();
        }
    }
}
