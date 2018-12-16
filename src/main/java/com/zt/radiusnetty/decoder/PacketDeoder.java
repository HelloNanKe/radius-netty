package com.zt.radiusnetty.decoder;

import com.zt.radiusnetty.packet.AccessRequest;
import com.zt.radiusnetty.packet.AccountingRequest;
import com.zt.radiusnetty.packet.BaseRequest;
import com.zt.radiusnetty.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/16 11:59
 */
public class PacketDeoder extends MessageToMessageDecoder<DatagramPacket> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        System.out.println("进入报文解析");
        ByteBuf byteBuf = datagramPacket.content();
        int capacity = byteBuf.readableBytes();
        System.out.println("总字节数:" + capacity);
        byte[] msg = new byte[capacity];
        byteBuf.readBytes(msg);

        for (byte b : msg) {
            System.out.print(b + " ");
        }

        //请求码
        int code = msg[0];
        //单次请求标识
        int identify = msg[1];
        //包长度
        int packetLenth = ByteUtil.doubleByteToInt(msg[2], msg[3]);


        byte[] authenticatorByte = new byte[16];
        System.arraycopy(msg, 4, authenticatorByte, 0, 16);
        String authenticator = new String(authenticatorByte,"UTF-8");

        //发生丢包现象，直接返回，等待重传
        if (capacity < packetLenth) {
            return;
        }

        System.out.println("authenticator=>" + authenticator);

        //认证请求报文
        if (code == 1) {
            AccessRequest accessRequest = new AccessRequest();
            accessRequest.setMessage(msg);
            accessRequest.setCode(code);
            accessRequest.setPacketLenth(packetLenth);
            accessRequest.setIdentifier(identify);
            accessRequest.setAuthenticator(authenticator);
            list.add(accessRequest);
        }

        //计费报文
        if (code == 4) {
            AccountingRequest accountingRequest = new AccountingRequest();
            accountingRequest.setMessage(msg);
            accountingRequest.setCode(code);
            accountingRequest.setPacketLenth(packetLenth);
            accountingRequest.setIdentifier(identify);
            accountingRequest.setAuthenticator(authenticator);
            list.add(accountingRequest);
        }


    }
}
