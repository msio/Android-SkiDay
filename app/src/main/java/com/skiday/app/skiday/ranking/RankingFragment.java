package com.skiday.app.skiday.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.social.SocialFragment;

/**
 * Created by msio on 4/27/17.
 */

public class RankingFragment extends Fragment {

    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost host = (TabHost) view.findViewById(R.id.tabHostRanking);
        host.setup();
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

        TabHost.TabSpec spec = host.newTabSpec("Durchgang 1");
        spec.setContent(R.id.tab1Ranking);
        spec.setIndicator("Durchgang 1");
        host.addTab(spec);

        spec = host.newTabSpec("Durchgang 2");
        spec.setContent(R.id.tab2Ranking);
        spec.setIndicator("Durchgang 2");
        host.addTab(spec);

        spec = host.newTabSpec("Gesamt");
        spec.setContent(R.id.tab3Ranking);
        spec.setIndicator("Gesamt");
        host.addTab(spec);

    }
}
