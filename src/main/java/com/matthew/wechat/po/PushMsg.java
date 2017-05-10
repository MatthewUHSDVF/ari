package com.matthew.wechat.po;

/**
 * Created by Administrator on 2017/4/20.
 */
public class PushMsg {
    private String touser;
    private String msgtype;
    private Text text;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
