package com.skiday.app.skiday.timeline;

import com.skiday.app.skiday.constants.Constants;
import com.skiday.app.skiday.model.Event;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.Results;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by msio on 5/2/17.
 */

public class EventsData {
    //Felix Neureuther is logged in
    static public int LOGGED_IN_USER_ID = 4;

    public EventsData() {

    }


    public static ArrayList<Event> generate() {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event(parseTime("9:00"), parseTime("10:15"), EventType.TRAINER, "Preparation Meeting", 0, "Room S3-C"));

        ArrayList<Person> persons = new ArrayList<Person>(Results.getResults().getPersons().values());

        ArrayList<Event> rounds1 = new ArrayList<Event>();
        rounds1.add(new Event(parseTime("10:00"), parseTime("10:05"), 1, persons.get(0)));
        rounds1.add(new Event(parseTime("10:10"), parseTime("10:15"), 1, persons.get(1)));
        rounds1.add(new Event(parseTime("10:20"), parseTime("10:25"), 1, persons.get(2)));
        rounds1.add(new Event(parseTime("10:30"), parseTime("10:35"), 1, persons.get(3)));
        rounds1.add(new Event(parseTime("10:40"), parseTime("10:45"), 1, persons.get(4)));
        rounds1.add(new Event(parseTime("10:50"), parseTime("10:55"), 1, persons.get(5)));
        rounds1.add(new Event(parseTime("11:00"), parseTime("11:05"), 1, persons.get(6)));
        rounds1.add(new Event(parseTime("11:10"), parseTime("11:15"), 1, persons.get(7)));
        rounds1.add(new Event(parseTime("11:20"), parseTime("11:25"), 1, persons.get(8)));
        rounds1.add(new Event(parseTime("11:30"), parseTime("11:35"), 1, persons.get(9)));

        events.add(new Event(EventType.LAP, "Round 1", 1, "Start", rounds1));

        events.add(new Event(parseTime("10:40"), parseTime("10:55"), EventType.PRESS, "Press ATV", 0, "Room Press"));
        events.add(new Event(parseTime("11:00"), parseTime("11:20"), EventType.SOCIAL, "Fan Window", 0, "Room S3-H"));
        events.add(new Event(parseTime("12:00"), parseTime("13:00"), EventType.TRAINER, "After Analysis Meeting", 0, "Room S3-C"));

        ArrayList<Event> rounds2 = new ArrayList<Event>();
        rounds2.add(new Event(parseTime("13:10"), parseTime("13:15"), 2, persons.get(0)));
        rounds2.add(new Event(parseTime("13:20"), parseTime("13:25"), 2, persons.get(1)));
        rounds2.add(new Event(parseTime("13:30"), parseTime("13:35"), 2, persons.get(2)));
        rounds2.add(new Event(parseTime("13:40"), parseTime("13:45"), 2, persons.get(3)));
        rounds2.add(new Event(parseTime("13:50"), parseTime("13:55"), 2, persons.get(4)));
        rounds2.add(new Event(parseTime("14:00"), parseTime("14:05"), 2, persons.get(5)));
        rounds2.add(new Event(parseTime("14:10"), parseTime("14:15"), 2, persons.get(6)));
        rounds2.add(new Event(parseTime("14:20"), parseTime("14:25"), 2, persons.get(7)));
        rounds2.add(new Event(parseTime("14:30"), parseTime("14:35"), 2, persons.get(8)));
        rounds2.add(new Event(parseTime("14:40"), parseTime("14:45"), 2, persons.get(9)));

        events.add(new Event(EventType.LAP, "Round 2", 2, "Start", rounds2));
        return events;
    }

    private static DateTime parseTime(String time) {
        return DateTimeFormat.forPattern(Constants.TIME_FORMAT).parseDateTime(time);
    }
}
