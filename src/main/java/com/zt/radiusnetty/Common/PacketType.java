package com.zt.radiusnetty.Common;

public class PacketType {

    /**
     * Packet type codes.
     */
    public static final int ACCESS_REQUEST = 1;
    public static final int ACCESS_ACCEPT = 2;
    public static final int ACCESS_REJECT = 3;
    public static final int ACCOUNTING_REQUEST = 4;
    public static final int ACCOUNTING_RESPONSE = 5;
    public static final int ACCOUNTING_STATUS = 6;
    public static final int PASSWORD_REQUEST = 7;
    public static final int PASSWORD_ACCEPT = 8;
    public static final int PASSWORD_REJECT = 9;
    public static final int ACCOUNTING_MESSAGE = 10;
    public static final int ACCESS_CHALLENGE = 11;
    public static final int STATUS_SERVER = 12;
    public static final int STATUS_CLIENT = 13;
    public static final int DISCONNECT_REQUEST = 40;    // RFC 2882
    public static final int DISCONNECT_ACK = 41;
    public static final int DISCONNECT_NAK = 42;
    public static final int COA_REQUEST = 43;
    public static final int COA_ACK = 44;
    public static final int COA_NAK = 45;
    public static final int STATUS_REQUEST = 46;
    public static final int STATUS_ACCEPT = 47;
    public static final int STATUS_REJECT = 48;
    public static final int RESERVED = 255;



}
