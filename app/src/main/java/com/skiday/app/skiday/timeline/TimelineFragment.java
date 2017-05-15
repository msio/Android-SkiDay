package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

import com.skiday.app.skiday.R;

/**
 * Created by msio on 4/27/17.
 */

public class TimelineFragment extends Fragment {

    Context context;
    TabHost host;

    public TimelineFragment() {
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
        super.onViewCreated(view, savedInstanceState);
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
        listViewEventCreator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Fragment fragment = EventDetailsFragment.newInstance();
                Bundle data = new Bundle();
                data.putSerializable("selected",(Event) parent.getAdapter().getItem(position));
                fragment.setArguments(data);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.content, fragment)
                        .addToBackStack("eventDetail").commit();
            }
        });
        listViewEventCreator.setAdapter(adapter1);

        final CustomArrayAdapter adapter2 = new CustomArrayAdapter(context, EventsData.generateMyEvents(), EventTab.MY_EVENT);
        listViewMyEvents.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Fragment fragment = EventDetailsFragment.newInstance();
                Bundle data = new Bundle();
                data.putSerializable("selected",(Event) parent.getAdapter().getItem(position));
                fragment.setArguments(data);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.content, fragment)
                        .addToBackStack("eventDetail").commit();
            }
        });
        listViewMyEvents.setAdapter(adapter2);

    }

}
