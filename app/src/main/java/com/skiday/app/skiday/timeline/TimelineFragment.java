package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.data.IEvent;
import com.skiday.app.skiday.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by msio on 4/27/17.
 */

public class TimelineFragment extends Fragment {

    Context context;

    public static TimelineFragment newInstance() {
        TimelineFragment fragment = new TimelineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = inflater.getContext();
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }


    private void addCalendarDayView(View view) {
        CalendarDayView dayView = (CalendarDayView) view.findViewById(R.id.calendar);
        dayView.setDecorator(new CustomDecoration(context));
        ArrayList<IEvent> events = new ArrayList<IEvent>();

        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY, 4);
        timeStart.set(Calendar.MINUTE, 0);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.set(Calendar.HOUR_OF_DAY, 4);
        timeEnd.set(Calendar.MINUTE, 20);
        Event event = new Event(1, timeStart, timeEnd, "1. Runde", "Vienna", Color.parseColor("#553fa9eb"));
        events.add(event);

        Calendar timeStart1 = Calendar.getInstance();
        timeStart1.set(Calendar.HOUR_OF_DAY, 4);
        timeStart1.set(Calendar.MINUTE, 20);
        Calendar timeEnd2 = Calendar.getInstance();
        timeEnd2.set(Calendar.HOUR_OF_DAY, 4);
        timeEnd2.set(Calendar.MINUTE, 50);

        event = new Event(2, timeStart1, timeEnd2, "Presse", "Vienna", Color.parseColor("#553fa9eb"));
        events.add(event);

        dayView.setEvents(events);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost host = (TabHost) view.findViewById(R.id.tabHost);
        host.setup();
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

        TabHost.TabSpec spec = host.newTabSpec("myEvents");
        spec.setContent(R.id.tab1);
        spec.setIndicator("My Events");
        host.addTab(spec);

        spec = host.newTabSpec("eventCreator");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Event Creator");
        host.addTab(spec);

        addCalendarDayView(view);
    }

}
