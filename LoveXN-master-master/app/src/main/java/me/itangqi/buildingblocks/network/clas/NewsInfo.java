package me.itangqi.buildingblocks.network.clas;

/**
 * Created by oreo on 2016/10/20.
 */
public class NewsInfo {
    private  int news_id;
    private  int news_user_id;
    private String news_title;
    private String news_content;
    private String news_picture_url;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int getNews_user_id() {
        return news_user_id;
    }

    public void setNews_user_id(int news_user_id) {
        this.news_user_id = news_user_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_picture_url() {
        return news_picture_url;
    }

    public void setNews_picture_url(String news_picture_url) {
        this.news_picture_url = news_picture_url;
    }

    public NewsInfo(int news_id, int news_user_id, String news_title, String news_content, String news_picture_url) {
        this.news_id = news_id;
        this.news_user_id = news_user_id;
        this.news_title = news_title;
        this.news_content = news_content;
        this.news_picture_url = news_picture_url;
    }
}
