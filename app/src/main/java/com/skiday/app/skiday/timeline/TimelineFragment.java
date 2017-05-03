package com.skiday.app.skiday.timeline;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.skiday.app.skiday.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by msio on 4/27/17.
 */

public class TimelineFragment extends Fragment {

    Context context;
    TabHost host;

    public TimelineFragment(){
        setArguments(new Bundle());
    }

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


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
        final ListView listViewMyEvents = (ListView) view.findViewById(R.id.timeline_myEvents);
        final ListView listViewEventCreator = (ListView) view.findViewById(R.id.timeline_eventCreator);

        host = (TabHost) view.findViewById(R.id.tabHost);
        host.setup();
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

        TabHost.TabSpec spec = host.newTabSpec("myEvents");
        spec.setContent(R.id.tab_myEvents);
        spec.setIndicator("My Events");
        host.addTab(spec);

        spec = host.newTabSpec("eventCreator");
        spec.setContent(R.id.tab_eventCreator);
        spec.setIndicator("Event Creator");
        host.addTab(spec);

        final CustomArrayAdapter adapter1 = new CustomArrayAdapter(context, EventsData.generateEventCreatorEvents(), EventTab.EVENT_CREATOR);
        listViewEventCreator.setAdapter(adapter1);
        listViewEventCreator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println();
                getArguments().putInt("tabHost",host.getCurrentTab());
                System.out.println("CURRENT "+host.getCurrentTab());
                /*Intent INT = new Intent(getActivity(), EventDetails.class);
                INT.putExtra("hi", "HI");
                startActivityForResult(INT,host.getCurrentTab());*/

                Fragment eventDetails = new EventDetails();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // For a little polish, specify a transition animation
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // To make it fullscreen, use the 'content' root view as the container
                // for the fragment, which is always the root view for the activity
                transaction.add(android.R.id.content, eventDetails)
                        .addToBackStack(null).commit();
            }
        });

        final CustomArrayAdapter adapter2 = new CustomArrayAdapter(context, EventsData.generateMyEvents(), EventTab.MY_EVENT);
        listViewMyEvents.setAdapter(adapter2);

    }

}
