package me.itangqi.buildingblocks.network.clas;

/**
 * Created by oreo on 2016/10/21.
 */
public class NoticeInfo {
    private  int college_notice_id;
    private  int college_notice_user_id;
    private String college_notice_title;
    private String college_notice_content;
    private String college_notice_picture_url;

    public int getCollege_notice_id() {
        return college_notice_id;
    }

    public void setCollege_notice_id(int college_notice_id) {
        this.college_notice_id = college_notice_id;
    }

    public int getCollege_notice_user_id() {
        return college_notice_user_id;
    }

    public void setCollege_notice_user_id(int college_notice_user_id) {
        this.college_notice_user_id = college_notice_user_id;
    }

    public String getCollege_notice_title() {
        return college_notice_title;
    }

    public void setCollege_notice_title(String college_notice_title) {
        this.college_notice_title = college_notice_title;
    }

    public String getCollege_notice_picture_url() {
        return college_notice_picture_url;
    }

    public void setCollege_notice_picture_url(String college_notice_picture_url) {
        this.college_notice_picture_url = college_notice_picture_url;
    }

    public String getCollege_notice_content() {
        return college_notice_content;
    }

    public void setCollege_notice_content(String college_notice_content) {
        this.college_notice_content = college_notice_content;
    }

    public NoticeInfo(int college_notice_id, int college_notice_user_id, String college_notice_picture_url, String college_notice_content, String college_notice_title) {
        this.college_notice_id = college_notice_id;
        this.college_notice_user_id = college_notice_user_id;
        this.college_notice_picture_url = college_notice_picture_url;
        this.college_notice_content = college_notice_content;
        this.college_notice_title = college_notice_title;
    }
}
