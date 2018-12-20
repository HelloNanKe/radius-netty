package com.zt.radiusnetty.init;

import com.zt.radiusnetty.decoder.AccessRequestDecoder;
import com.zt.radiusnetty.decoder.PacketDeoder;
import com.zt.radiusnetty.encoder.ResponsePacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Author: zt
 * @Date: 2018/12/19 10:13
 */
public class ServerChannelInit extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
        nioDatagramChannel.pipeline().addLast(new ObjectEncoder());
        nioDatagramChannel.pipeline().addLast(new ResponsePacketEncoder());
        nioDatagramChannel.pipeline().addLast(new PacketDeoder());
        nioDatagramChannel.pipeline().addLast(new AccessRequestDecoder());
    }
}
