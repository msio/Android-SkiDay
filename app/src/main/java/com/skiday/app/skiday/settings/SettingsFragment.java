package com.skiday.app.skiday.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TabHost;

import com.skiday.app.skiday.R;

public class SettingsFragment extends Fragment{
    private ExtendedModeFragment extendedModeFragment;

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost tabHost = (TabHost) view.findViewById(R.id.tabHost);
        assert(tabHost != null);

        tabHost.setup();

        TabHost.TabSpec account_settings = tabHost.newTabSpec("account_settings");
        account_settings.setIndicator("Account Settings");
        account_settings.setContent(R.id.tab1);

        tabHost.addTab(account_settings);

        final TabHost.TabSpec extended_mode = tabHost.newTabSpec("extended_mode");
        extended_mode.setIndicator("Extended Mode");
        extended_mode.setContent(R.id.tab2);

        tabHost.addTab(extended_mode);

        this.extendedModeFragment = ExtendedModeFragment.newInstance();
        loadFragmentTab(AccountSettingsFragment.newInstance(), R.id.tab1);
        loadFragmentTab(this.extendedModeFragment, R.id.tab2);


    }

    void loadFragmentTab(Fragment fragment, int tabID){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(tabID, fragment);
        fragmentTransaction.commit();
    }
}