package com.matthew.wechat.model.message.resp;

/**
 * Created by Administrator on 2017/4/19.
 */
public class TextMessage extends BaseMessage {
    //回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
