package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.Constants;
import com.skiday.app.skiday.model.Results;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Created by msio on 5/25/17.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "CustomExpandableListAdapter";

    private Context context;
    private ArrayList<Event> events;

    public CustomExpandableListAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public int getGroupCount() {
        return this.events.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.events.get(groupPosition).getRunsInLap() != null) {
            return this.events.get(groupPosition).getRunsInLap().size();
        }
        return 0;
    }

    @Override
    public Event getGroup(int groupPosition) {
        return this.events.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.events.get(groupPosition).getRunsInLap().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.timeline_list_item, parent, false);

        TextView desc = (TextView) rowView.findViewById(R.id.description);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        ImageView indicator = (ImageView) rowView.findViewById(R.id.indicator);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Event currentEvent = events.get(groupPosition);
        if (currentEvent.getType() == EventType.LAP) {
            imageView.setImageResource(R.drawable.ic_round);
            if (isExpanded) {
                indicator.setImageResource(R.drawable.expander_close_holo_light);
            } else {
                indicator.setImageResource(R.drawable.expander_open_holo_light);
            }

        } else if (currentEvent.getType() == EventType.SOCIAL) {
            imageView.setImageResource(R.drawable.ic_social);
        } else if (currentEvent.getType() == EventType.TRAINER) {
            imageView.setImageResource(R.drawable.ic_trainer);
        } else if (currentEvent.getType() == EventType.PRESS) {
            imageView.setImageResource(R.drawable.ic_press);
        }

        if (currentEvent.getType() == EventType.LAP) {
            desc.setText("Round " + currentEvent.getLap());
        } else {
            desc.setText(currentEvent.getDesc());
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
        if (currentEvent.getType() == EventType.LAP) {
            for (Event event : currentEvent.getRunsInLap()) {
                if (event.getPerson().getId() == EventsData.LOGGED_IN_USER_ID) {
                    time.setText(fmt.print(event.getStartTime()) + " - " + fmt.print(event.getEndTime()));
                }
            }
        } else {
            time.setText(fmt.print(currentEvent.getStartTime()) + " - " + fmt.print(currentEvent.getEndTime()));
        }

        return rowView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        System.out.println("GroupPosition "+groupPosition);
        final Event run = (Event) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.timeline_expanded_list_item, null);
        }

        TextView desc = (TextView) convertView.findViewById(R.id.name);
        desc.setText(run.getPerson().getName());

        ImageView picture = (ImageView) convertView.findViewById(R.id.profile_picture);
        Drawable drawable = context.getResources().getDrawable(run.getPerson().getPictureId());
        picture.setImageDrawable(drawable);

        TextView time = (TextView) convertView.findViewById(R.id.time);
        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.TIME_FORMAT);
        time.setText(fmt.print(run.getStartTime()) + " - " + fmt.print(run.getEndTime()));

        DateTime startTimeOfLoggedInUser = null;

        for (Event event : getGroup(groupPosition).getRunsInLap()) {
            if (event.getPerson().getId() == EventsData.LOGGED_IN_USER_ID) {
                startTimeOfLoggedInUser = event.getStartTime();
            }
        }

        if (run.getStartTime().isBefore(startTimeOfLoggedInUser)) {
            convertView.setAlpha((float) 0.4);

        }

        if (run.getPerson().getId() == EventsData.LOGGED_IN_USER_ID) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            desc.setTextColor(context.getResources().getColor(R.color.colorWhite));
            time.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
