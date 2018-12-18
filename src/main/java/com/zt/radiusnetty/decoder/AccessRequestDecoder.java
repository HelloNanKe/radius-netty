package com.zt.radiusnetty.decoder;

import com.zt.radiusnetty.packet.AccessRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import javax.security.sasl.SaslServer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/16 12:44
 */
public class AccessRequestDecoder extends MessageToMessageDecoder<AccessRequest> {


    @Override
    protected void decode(ChannelHandlerContext ctx, AccessRequest accessRequest, List<Object> out) throws Exception {
        System.err.println("进入accessRequest解码!");
        byte[] avpsMsg = accessRequest.getMessage();
        
        int size = avpsMsg.length;
        System.err.println("初始总长度：" + size);

        while (avpsMsg.length > 0) {

            int type = avpsMsg[0];
            int len = avpsMsg[1];
            byte[] tmpByte = new byte[len - 2];
            System.arraycopy(avpsMsg, 2, tmpByte, 0, len - 2);
            String val = new String(tmpByte, StandardCharsets.UTF_8);

            avpsMsg = getTlvByte(avpsMsg, len);

            System.out.println("type=" + type + " len=" + len + " val=" + val);

        }

    }


    private byte[] getTlvByte(byte[] src, int start) {
        byte[] target = new byte[src.length - start];
        System.arraycopy(src, start, target, 0, src.length - start);
        return target;
    }
}
