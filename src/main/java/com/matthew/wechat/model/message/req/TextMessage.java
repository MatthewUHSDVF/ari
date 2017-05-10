package com.matthew.wechat.model.message.req;

/**
 * Created by Administrator on 2017/4/18.
 */
public class TextMessage extends BaseMessage {
    //消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
