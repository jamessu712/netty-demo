package com.yy.netty.demo4;

import com.yy.netty.demo3.MyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
