package com.example.android3hw2.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MockerModel implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private Integer user;

    @SerializedName("group")
    private Integer group;


    public  MockerModel(){

    }
    public MockerModel( String title, String content, int user, int group) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
}

