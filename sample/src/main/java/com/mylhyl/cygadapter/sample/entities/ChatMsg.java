package com.mylhyl.cygadapter.sample.entities;

/**
 * Created by hupei on 2016/7/13.
 */
public class ChatMsg {
    public boolean isTo;
    public MsgType msgType;
    public String msgContent;
    public int msgImage;

    public ChatMsg() {
    }

    public ChatMsg(boolean isTo, String msgContent) {
        this.isTo = isTo;
        this.msgType = MsgType.TEXT;
        this.msgContent = msgContent;
    }

    public ChatMsg(boolean isTo, int msgImage) {
        this.isTo = isTo;
        this.msgType = MsgType.IMAGE;
        this.msgImage = msgImage;
    }

    public static enum MsgType {
        TEXT, IMAGE;
    }
}
