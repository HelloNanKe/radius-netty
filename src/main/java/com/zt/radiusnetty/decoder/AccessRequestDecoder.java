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
        System.err.println("进入accessRequest解码!");
        byte[] avpsMsg = accessRequest.getMessage();

      /*  int i = 0;
        for (byte b : avpsMsg) {
            System.err.print(b + " :" + i++ +" ");
            if(i%10==0){
                System.err.println();
            }
        }*/

        System.err.println();

        int size = avpsMsg.length;
        System.err.println("初始总长度：" + size);
        int typePos = 0;
        int lengthPos = 1;

        while (size > 0) {
            //属性字段按照TLV方式存储，T-type,L-length,V-value
            int type = avpsMsg[typePos];
            int length = avpsMsg[lengthPos];
            System.err.println("typePos=" + typePos + "=>lenthPos=" + lengthPos + "=>length=" + length);
            byte[] tmpByte = new byte[length];

            System.arraycopy(avpsMsg, typePos, tmpByte, 0, length);

            typePos = length + lengthPos + 1;
            lengthPos = typePos + 1;
            String value = null;
            if (length > 0) {
                value = new String(tmpByte, "UTF-8");
            }


            System.err.println("类型:" + type + "=>长度:" + length + "=>value:" + value);
            size = size - 2 - length;
            System.err.println("size=" + size);
        }

    }


}
