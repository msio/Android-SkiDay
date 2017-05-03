package com.skiday.app.skiday.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.timeline.TimelineFragment;

/**
 * Created by msio on 4/27/17.
 */

public class RankingFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RankingFragment";

    private String [][] rankData ={
            {"1.", "Marcel Hirscher", "1:19:85", "0", "0.3"},
            {"2.", "H. Kristoffersen", "1:20:10", "+0.1", "-0.2"},
            {"3.", "Hermann Maier", "1:22:85", "+0.3", "0"},
    };

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

        TabHost.TabSpec spec = host.newTabSpec("Lap 1");
        spec.setContent(R.id.tab1Ranking);
        spec.setIndicator("Lap 1");
        host.addTab(spec);

        spec = host.newTabSpec("Lap 2");
        spec.setContent(R.id.tab2Ranking);
        spec.setIndicator("Lap 2");
        host.addTab(spec);

        spec = host.newTabSpec("Gesamt");
        spec.setContent(R.id.tab3Ranking);
        spec.setIndicator("Gesamt");
        host.addTab(spec);

        setupTable(view);

    }

    public void setupTable(View view){

        TableLayout table = (TableLayout) view.findViewById(R.id.ranking_table_lap1);
        TableLayout table1 = (TableLayout) view.findViewById(R.id.ranking_table_lap2);
        TableLayout table2 = (TableLayout) view.findViewById(R.id.ranking_table_result);

        for (int i = 0; i< rankData.length; i++){

            table.addView(getRow(i));
            table1.addView(getRow(i));
            table2.addView(getRow(i));
        }

    }

    private TableRow getRow(int index){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);

        TableRow row = new TableRow(getContext());
        row.setId(index);

        row.setOnClickListener(this);

        // Rank
        TextView field = new TextView(getContext());
        field.setText(rankData[index][0]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setHeight(90);
        row.addView(field);

        // Name
        field = new TextView(getContext());
        field.setText(rankData[index][1]);
        field.setTextSize(13);
        field.setHeight(90);
        field.setTextColor(textColor);

        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(rankData[index][2]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setHeight(90);
        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(rankData[index][3]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(90);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(rankData[index][4]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(50);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;
    }

    @Override
    public void onClick(View v) {

        Log.i(TAG, "onClick: "+v.getId());

        Fragment fragment = RankDetailFragment.newInstance();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack("rankDetail").commit();
    }
}
