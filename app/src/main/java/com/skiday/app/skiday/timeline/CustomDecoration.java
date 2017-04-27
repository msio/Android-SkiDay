package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

/**
 * Created by msio on 4/24/17.
 */

public class CustomDecoration extends CdvDecorationDefault{

    Context context;
    public CustomDecoration(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public EventView getEventView(final IEvent event, Rect eventBound, int hourHeight,
                                  int seperateHeight) {

        final EventView eventView =
                super.getEventView(event, eventBound, hourHeight, 5);

        // hide event name
        TextView textEventName = (TextView) eventView.findViewById(com.framgia.library.calendardayview.R.id.item_event_name);
        textEventName.setVisibility(View.VISIBLE);

        // hide event header
        TextView textHeader1 = (TextView) eventView.findViewById(com.framgia.library.calendardayview.R.id.item_event_header_text1);
        TextView textHeader2 = (TextView) eventView.findViewById(com.framgia.library.calendardayview.R.id.item_event_header_text2);
        textHeader1.setVisibility(View.GONE);
        textHeader2.setVisibility(View.GONE);

        return eventView;
    }
}
