package com.example.bookingtable.db;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {

    private String id, pw, name, email, url;
    private String year, month, day, startHour, startMinute, seat, zone;

    public UserDTO() {}

    public UserDTO(String id, String pw, String name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }

    public UserDTO(String year, String month, String day, String startHour, String startMinute, String seat, String zone) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.seat = seat;
        this.zone = zone;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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


    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("pw", pw);
        result.put("name", name);
        result.put("email", email);
        result.put("url", url);
        result.put("year", year);
        result.put("month", month);
        result.put("day", day);
        result.put("startHour", startHour);
        result.put("startMinute", startMinute);
        result.put("seat", seat);
        result.put("zone", zone);

        return result;
    }

}
