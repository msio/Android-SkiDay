package com.skiday.app.skiday.ranking;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.model.MeantimeResult;
import com.skiday.app.skiday.model.MeantimeResultLine;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Result;
import com.skiday.app.skiday.model.Results;

/**
 * Created by jan on 03.05.17.
 */

public class RankDetailFragment extends Fragment {

    private static final String TAG = "RankingDetailFragment";

    private String [][] resultTable = {
            {"00:15.23", "+1.2", "-2.1"},
            {"00:35.10", "+0.5", "-1.2"},
            {"01:01,12", "+0,1", "-1.3"},
            {"01:19,85", "0", "+1.3"},
    };

    private int id;
    //PersonResult personResult;
    private int lapNumber;

    Person person;


    Results results = null;

    public static RankDetailFragment newInstance(int id, int lapNumber) {
        Log.i(TAG, "newInstance: ");
        RankDetailFragment fragment = new RankDetailFragment();
        fragment.setId(id);
        fragment.setLapNumber(lapNumber);
        return fragment;
    }

    public void setLapNumber(int lapNumber){
        this.lapNumber = lapNumber;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: "+id);
        person = RankingFragment.getResults().getPersons().get(id);

        return inflater.inflate(R.layout.fragment_rank_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        TextView field = (TextView) view.findViewById (R.id.name);
        field.setText(person.getName());

        field = (TextView) view.findViewById (R.id.rank_detail_lap);
        field.setText(lapNumber + ". Lap");

        MeantimeResultLine result3 = RankingFragment.getResults().getMeantimeResultLine(id, lapNumber, 4);
        field = (TextView) view.findViewById (R.id.current_time);
        field.setText(PersonResult.timeToString(result3.getTime()));

        ImageView profile = (ImageView) view.findViewById (R.id.profile_image);
        Drawable drawable = getResources().getDrawable(person.getPictureId());
        profile.setImageDrawable(drawable);

        TableLayout table = (TableLayout) view.findViewById(R.id.intermed_results);

        MeantimeResultLine result = RankingFragment.getResults().getMeantimeResultLine(id, lapNumber, 1);
        table.addView(getRow("1. Meantime", result));
        MeantimeResultLine result1 = RankingFragment.getResults().getMeantimeResultLine(id, lapNumber, 2);
        table.addView(getRow("2. Meantime", result1));
        result = RankingFragment.getResults().getMeantimeResultLine(id, lapNumber, 3);
        table.addView(getRow("3. Meantime", result));
        result = RankingFragment.getResults().getMeantimeResultLine(id, lapNumber, 4);
        table.addView(getResultRow("Total", result));


    }


    private TableRow getRow(String caption, MeantimeResultLine result){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);
        int headerColor = getResources().getColor(R.color.colorRankTableHeader);

        TableRow row = new TableRow(getContext());

        // Header
        TextView field = new TextView(getContext());
        field.setText(caption);
        field.setTextSize(13);
        field.setTextColor(headerColor);
        field.setHeight(90);
        field.setWidth(350);
        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getTime()));
        field.setTextSize(13);
        field.setHeight(90);
        field.setWidth(200);
        field.setTextColor(textColor);

        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getRelBest()));
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getRelMe()));
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;
    }


    private TableRow getResultRow(String caption, MeantimeResultLine result){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);
        int headerColor = getResources().getColor(R.color.colorRankTableHeader);
        int primaryColor = getResources().getColor(R.color.colorPrimary);

        TableRow row = new TableRow(getContext());

        // Header
        TextView field = new TextView(getContext());
        field.setText(caption);
        field.setTextSize(13);
        field.setTextColor(headerColor);
        field.setHeight(90);
        field.setWidth(350);
        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getTime()));
        field.setTextSize(13);
        field.setHeight(90);
        field.setWidth(200);
        field.setTextColor(primaryColor);

        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getRelBest()));
        field.setTextSize(13);
        field.setTextColor(primaryColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(PersonResult.timeToString(result.getRelMe()));
        field.setTextSize(13);
        field.setTextColor(primaryColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;

    }
}
