package com.matthew.wechat.model.message.resp;

/**
 * Created by Administrator on 2017/4/19.
 */
public class Music {
    //音乐名称
    private String Title;
    //音乐描述
    private String Description;
    //音乐链接
    private String MusicUrl;
    //高质量音乐连接，wifi环境优先使用该连接播放音乐
    private String HQMusicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }
}
