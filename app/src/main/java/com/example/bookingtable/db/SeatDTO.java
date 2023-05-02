package com.example.bookingtable.db;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SeatDTO {

    private String day, seat;


    public SeatDTO() {}

    public SeatDTO(String day, String seat) {
        this.day = day;
        this.seat = seat;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("day", day);
        result.put("seat", seat);

        return result;
    }
}
