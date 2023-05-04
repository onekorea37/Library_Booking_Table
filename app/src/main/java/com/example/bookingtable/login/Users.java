package com.example.bookingtable.login;

public class Users {

    private String userid, name, profile;
    private String url;
    private String year, month, day, startHour, startMinute, seat, zone;

    public Users(String userid, String name, String profile) {
        this.userid = userid;
        this.name = name;
        this.profile = profile;
    }

    public Users(String year, String month, String day, String startHour, String startMinute, String seat, String zone) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.seat = seat;
        this.zone = zone;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
