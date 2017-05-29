package com.skiday.app.skiday.timeline;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.EventType;
import com.skiday.app.skiday.constants.FilterType;
import com.skiday.app.skiday.model.Event;
import com.skiday.app.skiday.model.EventsData;

/**
 * Created by msio on 4/27/17.
 */

public class TimelineFragment extends Fragment {

    private Context context;
    private CustomExpandableListAdapter expandableListAdapter;
    private FilterType filterType = FilterType.ALL;
    private FilterType lastFilterType = FilterType.ALL;

    public TimelineFragment() {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter_button) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Filter");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    if (filterType == FilterType.LAPS) {
                        expandableListAdapter.showOnlyLaps(true);
                    } else {
                        expandableListAdapter.showOnlyLaps(false);
                    }
                }
            });
            final CharSequence[] items = {"All", "Laps"};
            builder.setSingleChoiceItems(items, filterType == FilterType.ALL ? 0 : 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //all item
                    if (which == 0) {
                        filterType = FilterType.ALL;
                        //laps item
                    } else if (which == 1) {
                        filterType = FilterType.LAPS;
                    }
                }
            });
            builder.create().show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.filter_button);
        item.setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.timeline_expandableListView);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Event event = expandableListAdapter.getGroup(groupPosition);
                if (event.getType() != EventType.LAP) {
                    Intent intent = new Intent(getActivity(), EventDetails.class);
                    intent.putExtra("event", event);
                    startActivity(intent);
                }
                return false;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Event event = expandableListAdapter.getChild(groupPosition, childPosition);
                Intent intent = new Intent(getActivity(), EventDetails.class);
                intent.putExtra("event", event);
                startActivity(intent);
                return false;
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        expandableListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        expandableListAdapter = new CustomExpandableListAdapter(this.context, EventsData.generate());
        expandableListView.setAdapter(expandableListAdapter);
    }

    private int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
