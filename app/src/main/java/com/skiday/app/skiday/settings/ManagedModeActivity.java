package com.skiday.app.skiday.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.skiday.app.skiday.R;

public class ManagedModeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_mode);
        Switch switchManagedMode = (Switch) findViewById(R.id.switchManagedMode);
        final EditText manager = (EditText) findViewById(R.id.manager);
        final EditText server = (EditText) findViewById(R.id.server);
        manager.setVisibility(View.GONE);
        server.setVisibility(View.GONE);
        switchManagedMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    manager.setVisibility(View.VISIBLE);
                    server.setVisibility(View.VISIBLE);
                } else {
                    manager.setVisibility(View.GONE);
                    server.setVisibility(View.GONE);
                }
            }
        });
    }
}