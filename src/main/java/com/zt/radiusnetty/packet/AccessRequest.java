package com.zt.radiusnetty.packet;

/**
 * @Author: zt
 * @Date: 2018/12/16 12:43
 */
public class AccessRequest extends BaseRequest {


    private String ip;
    private String userName;

    private byte[] passBytes;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getPassBytes() {
        return passBytes;
    }

    public void setPassBytes(byte[] passBytes) {
        this.passBytes = passBytes;
    }
}
