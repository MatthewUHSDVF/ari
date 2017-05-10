package com.matthew.wechat.model.message.resp;

/**
 * Created by Administrator on 2017/4/19.
 */
public class BaseMessage {
    //接收方账号
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间(整型)
    private long CreateTime;
    //消息类型
    private String MsgType;
    //位0x0001被标志时，星标刚收到的消息
    private int FunFlag;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getFunFlag() {
        return FunFlag;
    }

    public void setFunFlag(int funFlag) {
        FunFlag = funFlag;
    }
}
