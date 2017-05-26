package com.skiday.app.skiday.model;

import android.support.annotation.NonNull;

import com.skiday.app.skiday.timeline.EventType;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msio on 4/24/17.
 */

public class Event implements Comparable<Event>, Serializable {

    private DateTime startTime;
    private DateTime endTime;
    private String desc;
    private String location;
    private EventType type;
    private int lap;
    private ArrayList<Event> runsInLap;
    private Person person;


    public Event(DateTime startTime, DateTime endTime, EventType type, String desc, int lap, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
        this.type = type;
        this.location = location;
        this.lap = lap;
    }

    public Event(DateTime startTime, DateTime endTime, int lap, Person person) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = EventType.LAP;
        this.lap = lap;
        this.person = person;
    }


    public Event(EventType type, String desc, int lap, String location, ArrayList<Event> runsInLap) {
        this.runsInLap = runsInLap;
        this.desc = desc;
        this.type = type;
        this.location = location;
        this.lap = lap;
    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<Event> getRunsInLap() {
        return runsInLap;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
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
