package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.Constants;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by msio on 5/2/17.
 */

public class CustomArrayAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> events;
    private EventTab eventTab;

    public CustomArrayAdapter(Context context, ArrayList<Event> events, EventTab eventTab) {
        super(context, -1, events);
        this.context = context;
        Collections.sort(events);
        this.events = events;
        this.eventTab = eventTab;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.timeline_list_item, parent, false);


        TextView desc = (TextView) rowView.findViewById(R.id.description);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Event currentEvent = events.get(position);
        if (currentEvent.getType() == EventType.LAP) {
            imageView.setImageResource(R.drawable.ic_round);
        } else if (currentEvent.getType() == EventType.SOCIAL) {
            imageView.setImageResource(R.drawable.ic_social);
        } else if (currentEvent.getType() == EventType.TRAINER) {
            imageView.setImageResource(R.drawable.ic_trainer);
        } else if (currentEvent.getType() == EventType.PRESS) {
            imageView.setImageResource(R.drawable.ic_press);
        }

        if (currentEvent.getType() == EventType.LAP) {
            if (eventTab == EventTab.MY_EVENT) {
                desc.setText("Round " + currentEvent.getLap());
            } else {
                desc.setText(currentEvent.getDesc() + ", Round " + currentEvent.getLap());
            }
        } else {
            desc.setText(currentEvent.getDesc());
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
        time.setText(fmt.print(currentEvent.getStartTime()) + " - " + fmt.print(currentEvent.getEndTime()));
        return rowView;
    }
}
