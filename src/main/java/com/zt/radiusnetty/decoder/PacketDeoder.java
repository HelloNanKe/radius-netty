package com.zt.radiusnetty.decoder;

import com.zt.radiusnetty.packet.AccessRequest;
import com.zt.radiusnetty.packet.AccountingRequest;
import com.zt.radiusnetty.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/16 11:59
 */
public class PacketDeoder extends MessageToMessageDecoder<DatagramPacket> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
//        System.out.println("进入报文解析");
        ByteBuf byteBuf = datagramPacket.content();

        int capacity = byteBuf.readableBytes();
//        System.out.println("总字节数:" + capacity);
        byte[] msg = new byte[capacity];
        byteBuf.readBytes(msg);

        System.out.println();
        for (byte b : msg) {
            System.out.print(b + " ");
        }
        System.out.println();

        //请求码
        int code = msg[0];
        //单次请求标识
        int identify = msg[1];
        //包长度
        int packetLenth = ByteUtil.doubleByteToInt(msg[2], msg[3]);


        byte[] authenticator = new byte[16];
        System.arraycopy(msg, 4, authenticator, 0, 16);

        //发生丢包现象，直接返回，等待重传
        if (capacity < packetLenth) {
            System.out.println("发生丢包，等待重传！");
            return;
        }

//        System.out.println("authenticator=>" + authenticator);

        byte[] avpsByte = new byte[capacity - 20];
        System.arraycopy(msg, 20, avpsByte, 0, capacity - 20);


        //认证请求报文
        if (code == 1) {
            AccessRequest accessRequest = new AccessRequest();
            accessRequest.setMessage(avpsByte);
            accessRequest.setCode(code);
            accessRequest.setPacketLenth(packetLenth);
            accessRequest.setIdentifier(identify);
            accessRequest.setAuthenticator(authenticator);
            InetSocketAddress inetSocketAddress = datagramPacket.sender();
            accessRequest.setSenderAddress(inetSocketAddress);
            list.add(accessRequest);
        }

        //计费报文
        if (code == 4) {
            AccountingRequest accountingRequest = new AccountingRequest();
            accountingRequest.setMessage(avpsByte);
            accountingRequest.setCode(code);
            accountingRequest.setPacketLenth(packetLenth);
            accountingRequest.setIdentifier(identify);
            accountingRequest.setAuthenticator(authenticator);
            list.add(accountingRequest);
        }

    }
}
