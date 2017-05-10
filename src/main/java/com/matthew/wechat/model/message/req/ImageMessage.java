package com.matthew.wechat.model.message.req;

/**
 * Created by Administrator on 2017/4/19.
 */
public class ImageMessage extends BaseMessage {
    //图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
