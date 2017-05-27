package com.skiday.app.skiday.timeline;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skiday.app.skiday.R;


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
    }

    /*@Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Event data = (Event) getArguments().getSerializable("selected");
        TextView desc = (TextView) view.findViewById(R.id.description);
        desc.setText(data.getDesc());
        TextView roundLabel = (TextView) view.findViewById(R.id.roundLabel);
//        TextView round = (TextView) view.findViewById(R.id.round);

//        if (data.getType() == EventType.LAP) {
//            round.setText("" + data.getLap());
//        } else {
//            roundLabel.setVisibility(View.GONE);
//            round.setVisibility(View.GONE);
//        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
        TextView startTime = (TextView) view.findViewById(R.id.startTime);
        startTime.setText(fmt.print(data.getStartTime()));

        TextView endTime = (TextView) view.findViewById(R.id.endTime);
        endTime.setText(fmt.print(data.getEndTime()));

        TextView location = (TextView) view.findViewById(R.id.location);
        location.setText(data.getLocation());
    }*/
}