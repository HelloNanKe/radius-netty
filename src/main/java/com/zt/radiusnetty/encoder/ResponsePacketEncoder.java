package com.zt.radiusnetty.encoder;

import com.zt.radiusnetty.Common.PacketType;
import com.zt.radiusnetty.packet.AccessRequest;
import com.zt.radiusnetty.packet.ResponsePacket;
import com.zt.radiusnetty.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * 处理access_request的认证信息
 *
 * @Author: zt
 * @Date: 2018/12/18 17:18
 */
public class ResponsePacketEncoder extends MessageToByteEncoder<AccessRequest> {


    @Override
    protected void encode(ChannelHandlerContext ctx, AccessRequest msg, ByteBuf out) throws Exception {
        System.out.println("进入放回编码处理！AccessRequest");
        byte[] authenticator = msg.getAuthenticator();
        int identifier = msg.getIdentifier();
        int type = PacketType.ACCESS_ACCEPT;
        byte[] data = new byte[20];
        data[0] = (byte) type;
        data[1] = (byte) identifier;
        data[2] = (byte) 0;
        data[3] = (byte) 20;
        System.arraycopy(authenticator, 0, data, 4, 16);
        DatagramPacket datagramPacket = new DatagramPacket(data, 20);
        datagramPacket.setData(data);
        datagramPacket.setAddress(InetAddress.getLocalHost());
        datagramPacket.setPort(1812);
        ctx.writeAndFlush(datagramPacket);
//        out.writeBytes(data);
    }

}
