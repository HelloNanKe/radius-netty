package com.zt.radiusnetty.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;


public class SimpleTestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("收到消息");
        System.err.println("收到的内容:" + msg);

        if (msg instanceof DatagramPacket) {
            DatagramPacket datagramPacket = (DatagramPacket) msg;
            ByteBuf byteBuf = datagramPacket.content();

            //读取code 编号
            int code = byteBuf.readInt();
            //读取报文标识
            byte[] identity=new byte[1];
            byteBuf.readBytes(identity);
            String identitfier=new String(identity);


            //读取包长度
            byte[] packetLenth=new byte[2];
            byteBuf.readBytes(packetLenth);


            System.err.println("code=" + code);
            System.err.println("identifier="+identitfier);
            for(byte b:packetLenth){
                System.err.print(b);
            }

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
