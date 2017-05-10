package com.matthew.wechat.po;

/**
 * view类型的菜单
 * Created by Administrator on 2017/4/20.
 */
public class ViewButton extends Button {
    private String type;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
