package com.zt.radiusnetty.util;


import org.springframework.util.StringUtils;

public class ByteUtil {

    //单字节转换成int
    public static int oneByteToInt(byte num){
        return num & 0xff;
    }

    //双字节转换成int
    public static int doubleByteToInt(byte first,byte second){
        return second & 0xff | (first & 0xff) << 8;
    }

    //字节转换成String
    public static String byteToHex(byte b){
        String hex = Integer.toHexString(b&0xff);

        if (StringUtils.isEmpty(hex)){
            return "00";
        }

        if (hex.length() < 2){
            hex = "0"+hex;
        }

        return hex;

    }

    public static String bytesToHex(byte[] bs){
        String hex = "";
        for (byte b:bs){
            String t = Integer.toHexString(b&0xff);

            if (StringUtils.isEmpty(t)){
                t = "00";
            }

            if (t.length() < 2){
                t = "0"+t;
            }
            hex += t;
        }
        return hex;
    }


    public static void main(String[] args){
        System.out.println(oneByteToInt((byte)0xff));
    }

}
