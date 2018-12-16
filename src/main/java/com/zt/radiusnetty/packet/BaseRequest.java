package com.zt.radiusnetty.packet;

/**
 * @Author: zt
 * @Date: 2018/12/16 13:02
 */
public class BaseRequest {

    /**
     * AVPs属性字段
     */
    private byte[] message;

    /**
     * 请求标识符 1字节
     */
    private int code;

    /**
     * 包长度 2字节
     */
    private int packetLenth;

    /**
     * 单次请求标识 1字节
     */
    private int identifier;

    /**
     * 认证字域占用16个字节，用于Radius Client 和Server之间消息认证的有效性，和密码隐藏算法。
     */
    private String authenticator;

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPacketLenth() {
        return packetLenth;
    }

    public void setPacketLenth(int packetLenth) {
        this.packetLenth = packetLenth;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(String authenticator) {
        this.authenticator = authenticator;
    }
}
