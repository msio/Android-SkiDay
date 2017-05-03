package com.skiday.app.skiday.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.skiday.app.skiday.R;

/**
 * Created by jan on 03.05.17.
 */

public class RankDetailFragment extends Fragment {

    private static final String TAG = "RankingDetailFragment";

    private String [][] resultTable = {
            {"00:15.23", "+1.2", "-2.1"},
            {"00:35.10", "+0.5", "-1.2"},
            {"01:01,12", "+0,1", "-1.3"},
    };

    private String result = "01:19,85";

    public static RankDetailFragment newInstance() {
        Log.i(TAG, "newInstance: ");
        RankDetailFragment fragment = new RankDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_rank_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        TableLayout table = (TableLayout) view.findViewById(R.id.intermed_results);

        table.addView(getRow(0));
        table.addView(getRow(1));
        table.addView(getRow(2));
        table.addView(getResultRow());

    }


    private TableRow getRow(int index){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);
        int headerColor = getResources().getColor(R.color.colorRankTableHeader);

        TableRow row = new TableRow(getContext());

        // Header
        TextView field = new TextView(getContext());
        field.setText((index+1)+". Meantime");
        field.setTextSize(13);
        field.setTextColor(headerColor);
        field.setHeight(90);
        field.setWidth(350);
        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(resultTable[index][0]);
        field.setTextSize(13);
        field.setHeight(90);
        field.setWidth(200);
        field.setTextColor(textColor);

        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(resultTable[index][1]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(resultTable[index][2]);
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;
    }


    private TableRow getResultRow(){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);
        int headerColor = getResources().getColor(R.color.colorRankTableHeader);

        TableRow row = new TableRow(getContext());

        // Header
        TextView field = new TextView(getContext());
        field.setText("Total:");
        field.setTextSize(13);
        field.setTextColor(headerColor);
        field.setHeight(90);
        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(result);
        field.setTextSize(13);
        field.setHeight(90);
        field.setTextColor(textColor);

        row.addView(field);

        return row;
    }
}
