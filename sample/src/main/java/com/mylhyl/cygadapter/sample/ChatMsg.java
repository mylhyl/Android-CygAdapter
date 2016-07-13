package com.mylhyl.cygadapter.sample;

/**
 * Created by hupei on 2016/7/13.
 */
public class ChatMsg {
    public boolean isTo;
    public String msg;

    public ChatMsg() {
    }

    public ChatMsg(boolean isTo, String msg) {
        this.isTo = isTo;
        this.msg = msg;
    }
}
