package com.skiday.app.skiday.timeline;

import com.skiday.app.skiday.constants.Constants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;

/**
 * Created by msio on 5/2/17.
 */

public class EventsData {

    public EventsData() {

    }


    public static ArrayList<Event> generateMyEvents() {
        //NEUREUTHER Felix is logged in
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event(parseTime("9:00"), parseTime("10:15"), EventType.TRAINER, "Preparation Meeting", 0, "Room S3-C"));
        events.add(new Event(parseTime("10:30"), parseTime("10:35"), EventType.ROUND, "", 1, "Start"));
        events.add(new Event(parseTime("10:40"), parseTime("10:55"), EventType.PRESS, "Press ATV", 0, "Room Press"));
        events.add(new Event(parseTime("11:00"), parseTime("11:20"), EventType.SOCIAL, "Fan Window", 0, "Room S3-H"));
        events.add(new Event(parseTime("12:00"), parseTime("13:00"), EventType.TRAINER, "After Analysis Meeting", 0, "Room S3-C"));
        events.add(new Event(parseTime("13:30"), parseTime("13:35"), EventType.ROUND, "", 2, "Start"));
        return events;
    }

    public static ArrayList<Event> generateEventCreatorEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event(parseTime("10:00"), parseTime("10:05"), EventType.ROUND, "HIRSCHER Marcel", 1, "Start"));
        events.add(new Event(parseTime("10:10"), parseTime("10:15"), EventType.ROUND, "JANSRUD Kjetil", 1, "Start"));
        events.add(new Event(parseTime("10:20"), parseTime("10:25"), EventType.ROUND, "MYHRER Andre", 1, "Start"));
        events.add(new Event(parseTime("10:30"), parseTime("10:35"), EventType.ROUND, "NEUREUTHER Felix", 1, "Start"));
        events.add(new Event(parseTime("10:40"), parseTime("10:45"), EventType.ROUND, "MATT Michael", 1, "Start"));
        events.add(new Event(parseTime("10:50"), parseTime("10:55"), EventType.ROUND, "MOELGG Manfred", 1, "Start"));
        events.add(new Event(parseTime("11:00"), parseTime("11:05"), EventType.ROUND, "HARGIN Mattias", 1, "Start"));
        events.add(new Event(parseTime("11:10"), parseTime("11:15"), EventType.ROUND, "KHOROSHILOV Alexander", 1, "Start"));
        events.add(new Event(parseTime("11:20"), parseTime("11:25"), EventType.ROUND, "RYDING Dave", 1, "Start"));
        events.add(new Event(parseTime("11:30"), parseTime("11:35"), EventType.ROUND, "GROSS Stefano", 1, "Start"));
        events.add(new Event(parseTime("11:40"), parseTime("11:45"), EventType.ROUND, "AERNI Luca", 1, "Start"));
        return events;
    }

    private static DateTime parseTime(String time) {
        return DateTimeFormat.forPattern(Constants.TIME_FORMAT).parseDateTime(time);
    }
}
