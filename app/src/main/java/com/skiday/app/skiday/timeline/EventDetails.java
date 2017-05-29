package com.skiday.app.skiday.timeline;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.Constants;
import com.skiday.app.skiday.constants.EventType;
import com.skiday.app.skiday.model.Event;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by msio on 5/2/17.
 */

public class EventDetails extends AppCompatActivity {

    private static final String TAG = "EventDetails";

    public static EventDetails newInstance() {
        EventDetails fragment = new EventDetails();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("event");

        TextView desc = (TextView) findViewById(R.id.description);
        desc.setText(event.getDesc());
        TextView lapLabel = (TextView) findViewById(R.id.lapLabel);
        TextView lap = (TextView) findViewById(R.id.lap);

        if (event.getType() == EventType.LAP) {
            lap.setText(""+event.getLap());
        } else {
            lapLabel.setVisibility(View.GONE);
            lap.setVisibility(View.GONE);
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
        TextView startTime = (TextView) findViewById(R.id.startTime);
        startTime.setText(fmt.print(event.getStartTime()));

        TextView endTime = (TextView) findViewById(R.id.endTime);
        endTime.setText(fmt.print(event.getEndTime()));

        TextView location = (TextView) findViewById(R.id.location);
        location.setText(event.getLocation());

    }
}