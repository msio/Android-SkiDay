package com.skiday.app.skiday.model;

import com.skiday.app.skiday.constants.Constants;
import com.skiday.app.skiday.constants.EventType;
import com.skiday.app.skiday.model.Event;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.Results;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;

/**
 * Created by msio on 5/2/17.
 */

public class EventsData {
    //Felix Neureuther is logged in
    static public int LOGGED_IN_USER_ID = 4;

    public EventsData() {

    }

    private static ArrayList<Event> generateLapEvents(DateTime from, DateTime to, ArrayList<Person> persons) {
        ArrayList<Event> rounds = new ArrayList<Event>();

        for (int i = 0; i < persons.size(); i++) {
            rounds.add(new Event(from, to, 1, persons.get(i)));
            from = from.plusMinutes(10);
            to = to.plusMinutes(10);
        }
        return rounds;
    }

    public static ArrayList<Event> generate() {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event(parseTime("9:00"), parseTime("10:15"), EventType.TRAINER, "Preparation Meeting", 0, "Room S3-C"));

        ArrayList<Person> persons = new ArrayList<Person>(Results.getResults().getPersons().values());

        events.add(new Event(EventType.LAP, "Round 1", 1, "Start", generateLapEvents(parseTime("10:00"),parseTime("10:05"),persons)));
        events.add(new Event(parseTime("10:40"), parseTime("10:55"), EventType.PRESS, "Press ATV", 0, "Room Press"));
        events.add(new Event(parseTime("11:00"), parseTime("11:20"), EventType.SOCIAL, "Fan Window", 0, "Room S3-H"));
        events.add(new Event(parseTime("12:00"), parseTime("13:00"), EventType.TRAINER, "After Analysis Meeting", 0, "Room S3-C"));
        events.add(new Event(EventType.LAP, "Round 2", 2, "Start", generateLapEvents(parseTime("13:10"),parseTime("13:15"),persons)));
        events.add(new Event(parseTime("14:10"), parseTime("15:00"), EventType.TRAINER, "Team Meeting", 0, "Room S1-D"));
        events.add(new Event(parseTime("15:15"), parseTime("15:30"), EventType.PRESS, "Press ORF", 0, "Room M3-D"));
        events.add(new Event(EventType.LAP, "Round 3", 3, "Start", generateLapEvents(parseTime("15:30"),parseTime("15:35"),persons)));

        return events;
    }

    private static DateTime parseTime(String time) {
        return DateTimeFormat.forPattern(Constants.TIME_FORMAT).parseDateTime(time);
    }
}
