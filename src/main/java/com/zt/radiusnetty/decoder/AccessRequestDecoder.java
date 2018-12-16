package com.zt.radiusnetty.decoder;

import com.zt.radiusnetty.packet.AccessRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/16 12:44
 */
public class AccessRequestDecoder extends MessageToMessageDecoder<AccessRequest> {


    @Override
    protected void decode(ChannelHandlerContext ctx, AccessRequest accessRequest, List<Object> out) throws Exception {
        System.out.println("进入accessRequest解码!");
        byte[] packet = accessRequest.getMessage();

    }


}
