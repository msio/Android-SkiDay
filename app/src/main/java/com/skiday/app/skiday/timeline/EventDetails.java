package com.skiday.app.skiday.timeline;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.skiday.app.skiday.R;


/**
 * Created by msio on 5/2/17.
 */

public class EventDetails extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Bundle args = getArguments();
//        String title = args.getString("title");
        View v = inflater.inflate(R.layout.dialog_event_details, container, false);
//        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        toolbar.setTitle("TEST");
        return v;
    }

}