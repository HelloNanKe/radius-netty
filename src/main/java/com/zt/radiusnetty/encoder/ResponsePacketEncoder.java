package com.zt.radiusnetty.encoder;

import com.zt.radiusnetty.Common.PacketType;
import com.zt.radiusnetty.packet.AccessRequest;
import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

/**
 * 处理access_request的认证信息
 *
 * @Author: zt
 * @Date: 2018/12/18 17:18
 */
public class ResponsePacketEncoder extends MessageToMessageEncoder<AccessRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AccessRequest msg, List<Object> out) throws Exception {
        System.out.println("进入返回编码处理！AccessRequest");
        byte[] authenticator = msg.getAuthenticator();
        int identifier = msg.getIdentifier();
        int type = PacketType.ACCESS_ACCEPT;
        byte[] data = new byte[20];
        data[0] = (byte) type;
        data[1] = (byte) identifier;
        data[2] = (byte) 0;
        data[3] = (byte) 20;
        System.arraycopy(authenticator, 0, data, 4, 16);

        ByteBuf byteBuf =Unpooled.copiedBuffer(data);
        DatagramPacket datagramPacket = new DatagramPacket(byteBuf, msg.getSenderAddress());
        ctx.writeAndFlush(datagramPacket);
    }
}
