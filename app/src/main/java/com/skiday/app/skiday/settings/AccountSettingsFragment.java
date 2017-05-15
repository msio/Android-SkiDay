package com.skiday.app.skiday.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.social.SocialFragment;

public class AccountSettingsFragment extends Fragment{
    private Button changePasswordButton;

    public static AccountSettingsFragment newInstance() {
        AccountSettingsFragment fragment = new AccountSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.changePasswordButton = (Button)view.findViewById(R.id.change_password_button);
        assert(this.changePasswordButton != null);

        this.changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Password changed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}