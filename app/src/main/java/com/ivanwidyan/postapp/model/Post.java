package com.ivanwidyan.postapp.model;

/**
 * Created by ivanwidyan on 10/20/17.
 */

public class Post {

    private String title;
    private String desc;
    private String image;

    public Post() {

    }

    public Post(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
