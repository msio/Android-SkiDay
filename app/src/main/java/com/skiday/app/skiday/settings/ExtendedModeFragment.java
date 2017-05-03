package com.skiday.app.skiday.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.skiday.app.skiday.R;

public class ExtendedModeFragment extends Fragment{
    private EditText teamServerEditText;
    private EditText timelineTeamEditText;
    private Switch enable_switch;

    public static ExtendedModeFragment newInstance() {
        ExtendedModeFragment fragment = new ExtendedModeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_extended_mode, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.teamServerEditText = (EditText)view.findViewById(R.id.team_edit_text);
        assert(this.teamServerEditText != null);

        this.timelineTeamEditText = (EditText)view.findViewById(R.id.timeline_edit_text);
        assert(this.timelineTeamEditText != null);


        this.enable_switch = (Switch)view.findViewById(R.id.switch1);
        assert(this.enable_switch != null);

        this.enable_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                enableExtendedMode(isChecked);
            }
        });
    }

    public void enableExtendedMode(boolean state){
        this.timelineTeamEditText.setFocusable(state);
        this.timelineTeamEditText.setText("");
        this.teamServerEditText.setFocusable(state);
        this.teamServerEditText.setText("");
    }
}