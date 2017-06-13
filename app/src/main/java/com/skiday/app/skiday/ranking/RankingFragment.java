package com.skiday.app.skiday.ranking;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;
import com.skiday.app.skiday.model.MeantimeResultLine;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Results;


/**
 * Created by msio on 4/27/17.
 */

public class RankingFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RankingFragment";

    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Ranking");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        spec = host.newTabSpec("Total");
        spec.setContent(R.id.tab3Ranking);
        spec.setIndicator("Total");
        host.addTab(spec);

        setupTable(view);
    }


    LayoutInflater mInflater = null;

    public void setupTable(View view) {

        TableLayout table = (TableLayout) view.findViewById(R.id.ranking_table_lap1);
        TableLayout table1 = (TableLayout) view.findViewById(R.id.ranking_table_lap2);
        TableLayout table2 = (TableLayout) view.findViewById(R.id.ranking_table_result);

        int rank = 1;
        for (MeantimeResultLine line : Results.getResults().getLapResultTable(1)) {
            table.addView(getRow(line, rank, 1));
            rank++;
        }
        rank = 1;
        for (MeantimeResultLine line : Results.getResults().getLapResultTable(2)) {
            table1.addView(getRow(line, rank, 2));
            rank++;
        }
        rank = 1;
        for (MeantimeResultLine line : Results.getResults().getLapResultTable(3)) {
            table2.addView(getRow(line, rank, 3));
            rank++;
        }
    }

    private View getRow(MeantimeResultLine line, int rank, int lapNumber) {

        Log.i(TAG, "getRow: " + line);

        View listItemView = mInflater.inflate(R.layout.rank_list_item, null);
        listItemView.setId(line.getPerson().getId());
        listItemView.setTag(R.integer.lapNumber, lapNumber);
        listItemView.setOnClickListener(this);

        TextView view = (TextView) listItemView.findViewById(R.id.rank_list_item_name);
        view.setText(line.getPerson().getName());

        view = (TextView) listItemView.findViewById(R.id.rank_list_item_rank);
        view.setText(rank + ".");

        ImageView imgView = (ImageView) listItemView.findViewById(R.id.rank_list_item_profile_image);
        Drawable drawable = getResources().getDrawable(line.getPerson().getPictureId());
        imgView.setImageDrawable(drawable);

        imgView = (ImageView) listItemView.findViewById(R.id.rank_list_item_profile_best_icon);

        if (line.getRelBest() != 0)
            imgView.setImageDrawable(null);

        view = (TextView) listItemView.findViewById(R.id.rank_list_item_time);
        view.setText(PersonResult.timeToString(line.getTime()));

        view = (TextView) listItemView.findViewById(R.id.rank_list_item_rel_best);
        view.setText(PersonResult.timeToString(line.getRelBest()));

        view = (TextView) listItemView.findViewById(R.id.rank_list_item_rel_me);
        view.setText(PersonResult.timeToString(line.getRelMe()));

        return listItemView;
    }

    @Override
    public void onClick(View v) {

        Log.i(TAG, "onClick: " + v.getId());

        int lapNumber = (int) v.getTag(R.integer.lapNumber);

        if (lapNumber == 3) return;

        Intent intent = new Intent(getActivity(), RankDetails.class);
        intent.putExtra("rankId", v.getId());
        intent.putExtra("rankLapNumber", lapNumber);
        intent.putExtra("live", false);
        startActivity(intent);
    }
}
