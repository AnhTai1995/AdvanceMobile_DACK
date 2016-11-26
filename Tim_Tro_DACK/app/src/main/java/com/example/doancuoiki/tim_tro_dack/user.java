package com.example.doancuoiki.tim_tro_dack;

/**
 * Created by xuan trung on 11/19/2016.
 */

public class user {

    String name;
    String avatar;
    String timePost;

    public user(){
    }

    public user(String avatar, String name, String timePost){
        this.avatar = avatar;
        this.name = name;
        this.timePost = timePost;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }

    public String getTimePost() {
        return timePost;
    }
}
