package com.example.bookingtable.login;

public class Users {

    private String userid, name, profile;
    private String url;

    public Users(String userid, String name, String profile) {
        this.userid = userid;
        this.name = name;
        this.profile = profile;
    }

    public Users() {}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
