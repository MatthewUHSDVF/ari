package com.matthew.menu;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class WxMenuButton {

    private String type;
    private String name;
    private String key;
    private String url;
    private List<WxMenuButton> subButtons;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WxMenuButton> getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(List<WxMenuButton> subButtons) {
        this.subButtons = subButtons;
    }
}
