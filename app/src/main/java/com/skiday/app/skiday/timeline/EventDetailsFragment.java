package com.skiday.app.skiday.timeline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.Constants;
import com.skiday.app.skiday.model.Event;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by msio on 5/2/17.
 */

public class EventDetailsFragment extends Fragment {

    private static final String TAG = "EventDetailsFragment";

    public static EventDetailsFragment newInstance() {
        EventDetailsFragment fragment = new EventDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
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
    }
}