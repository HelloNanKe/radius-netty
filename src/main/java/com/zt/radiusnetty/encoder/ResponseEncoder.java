package com.zt.radiusnetty.encoder;

import com.zt.radiusnetty.packet.ResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/18 17:18
 */
public class ResponseEncoder extends MessageToByteEncoder<ResponsePacket> {


    @Override
    protected void encode(ChannelHandlerContext ctx, ResponsePacket msg, ByteBuf out) throws Exception {
        System.out.println("进入放回编码处理！");
    }

}
