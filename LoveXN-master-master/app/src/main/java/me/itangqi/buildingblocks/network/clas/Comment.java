package me.itangqi.buildingblocks.network.clas;

/**
 * Created by oreo on 2016/10/15.
 */
public class Comment {
    private int comment_id;
    private String comment_time;
    private int user_id;
    private int notice_id;
    private String comment_content;
    private String user_name;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Comment(int comment_id, String comment_time, int user_id, int notice_id, String comment_content, String user_name) {
        this.comment_id = comment_id;
        this.comment_time = comment_time;
        this.user_id = user_id;
        this.notice_id = notice_id;
        this.comment_content = comment_content;
        this.user_name = user_name;
    }
}
