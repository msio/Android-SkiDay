package com.skiday.app.skiday.timeline;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by msio on 4/24/17.
 */

public class Event implements Comparable<Event>,Serializable{

    private DateTime startTime;
    private DateTime endTime;
    private String desc;
    private String location;
    private EventType type;
    private int round;

    public Event(DateTime startTime, DateTime endTime, EventType type, String desc,int round, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
        this.type = type;
        this.location = location;
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public int compareTo(@NonNull Event o) {
        return startTime.compareTo(o.getStartTime());
    }
}
