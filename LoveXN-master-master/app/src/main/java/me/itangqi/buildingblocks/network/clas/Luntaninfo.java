package me.itangqi.buildingblocks.network.clas;

/**
 * Created by oreo on 2016/10/15.
 */
public class Luntaninfo {
    private  int topic_id;
    private String topic_title;
    private String topic_content;
    private String topic_picture_url;

    public Luntaninfo(int topic_id, String topic_title, String topic_content,String pic_exist) {
        this.topic_id = topic_id;
        this.topic_title = topic_title;
        this.topic_content = topic_content;
        this.topic_picture_url = pic_exist;
    }

    public String getTopic_picture_url() {
        return topic_picture_url;
    }

    public void setTopic_picture_url(String topic_picture_url) {
        this.topic_picture_url = topic_picture_url;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }
}
