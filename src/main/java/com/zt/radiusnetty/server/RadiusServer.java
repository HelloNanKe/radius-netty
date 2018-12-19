package com.zt.radiusnetty.server;

import com.zt.radiusnetty.init.ServerChannelInit;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class RadiusServer {

    public static Channel channel;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup);
        bootstrap.channel(NioDatagramChannel.class);

        bootstrap.handler(new ServerChannelInit())
                .option(ChannelOption.SO_BROADCAST, true)// 支持广播
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                .option(ChannelOption.SO_SNDBUF, 1024 * 1024);// 设置UDP写缓冲区为1M
        try {
            ChannelFuture future = bootstrap.bind(1812).sync();
            channel = future.channel();
            future.channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }


    }

}
