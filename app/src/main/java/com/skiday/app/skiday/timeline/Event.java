package com.skiday.app.skiday.timeline;

import com.framgia.library.calendardayview.data.IEvent;

import java.util.Calendar;

/**
 * Created by msio on 4/24/17.
 */

public class Event implements IEvent {

    private long id;
    private Calendar startTime;
    private Calendar endTime;
    private String name;
    private String location;
    private int color;

    public Event(long id, Calendar startTime, Calendar endTime, String name, String location, int color) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.location = location;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    @Override
    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
