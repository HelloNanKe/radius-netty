package com.zt.radiusnetty.packet;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: zt
 * @Date: 2018/12/18 17:18
 */
public class ResponsePacket {

    private AccessRequest accessRequest;

    public AccessRequest getAccessRequest() {
        return accessRequest;
    }

    public void setAccessRequest(AccessRequest accessRequest) {
        this.accessRequest = accessRequest;
    }
}
