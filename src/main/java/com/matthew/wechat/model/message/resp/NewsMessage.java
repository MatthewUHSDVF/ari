package com.matthew.wechat.model.message.resp;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class NewsMessage extends BaseMessage {
    //图文消息个数,限制为10调以内
    private int ArticleCount;
    //多条图文消息信息，默认第一个item为大图
    private List<Article> articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
