package com.zt.radiusnetty.decoder;

import com.zt.radiusnetty.packet.AccessRequest;
import com.zt.radiusnetty.util.ByteUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: zt
 * @Date: 2018/12/16 12:44
 */
public class AccessRequestDecoder extends MessageToMessageDecoder<AccessRequest> {


    @Override
    protected void decode(ChannelHandlerContext ctx, AccessRequest accessRequest, List<Object> out) throws Exception {
        System.out.println("进入accessRequest解码!");
        byte[] avpsMsg = accessRequest.getMessage();
        int size = avpsMsg.length;
        System.out.println("初始总长度：" + size);
   /*     int k = 1;
        for (byte b : avpsMsg) {
            System.out.print(b + " ");
            if (k++ % 10 == 0)
                System.out.println();
        }
        System.out.println();*/
        while (avpsMsg.length > 0) {
            int type = avpsMsg[0];
            int len = avpsMsg[1];
            byte[] tmpByte = new byte[len - 2];
            byte[] passBytes;
            System.arraycopy(avpsMsg, 2, tmpByte, 0, len - 2);
            String val = "";
            //ip需要当成4个数字特殊解码
            if (type == 4) {
                for (int flag = 0; flag < tmpByte.length; flag++) {
                    if (flag == tmpByte.length - 1) {
                        val += ByteUtil.oneByteToInt(tmpByte[flag]) + "";
                    } else {
                        val += ByteUtil.oneByteToInt(tmpByte[flag]) + ".";
                    }
                    accessRequest.setIp(val);
                }
            } else if (type == 2) {
                //密码的字节码被加密不能直接转String
                passBytes = new byte[tmpByte.length];
                System.arraycopy(tmpByte, 0, passBytes, 0, tmpByte.length);
                accessRequest.setPassBytes(passBytes);
            } else {
                val = new String(tmpByte, StandardCharsets.UTF_8);
            }

            if (type == 1) {
                accessRequest.setUserName(val);
            }
            avpsMsg = getTlvByte(avpsMsg, len);
            System.out.println("type=" + type + " len=" + len + " val=" + val);
        }
        ctx.writeAndFlush(accessRequest);
    }


    private byte[] getTlvByte(byte[] src, int start) {
        byte[] target = new byte[src.length - start];
        System.arraycopy(src, start, target, 0, src.length - start);
        return target;
    }

}
