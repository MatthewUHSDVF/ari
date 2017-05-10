package com.matthew.wechat.model.message.resp;

/**
 * Created by Administrator on 2017/4/19.
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }
    public void setMusic(Music music) {
        Music = music;
    }
}
