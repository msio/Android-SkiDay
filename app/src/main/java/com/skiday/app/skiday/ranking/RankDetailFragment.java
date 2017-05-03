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
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Result;

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
    PersonResult personResult;

    public static RankDetailFragment newInstance(int id) {
        Log.i(TAG, "newInstance: ");
        RankDetailFragment fragment = new RankDetailFragment();
        fragment.setId(id);
        return fragment;
    }

    public void setId(int id){
        this.id = id;
    }
        //personResult = RankingFragment.getData().get(index);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        personResult = RankingFragment.getData().get(id-1);



        return inflater.inflate(R.layout.fragment_rank_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        TextView field = (TextView) view.findViewById (R.id.name);
        field.setText(personResult.getName());

        field = (TextView) view.findViewById (R.id.current_time);
        field.setText(personResult.getTotal().getTime());

        ImageView profile = (ImageView) view.findViewById (R.id.profile_image);
        Drawable drawable = getResources().getDrawable(personResult.getPictureId());
        profile.setImageDrawable(drawable);

        TableLayout table = (TableLayout) view.findViewById(R.id.intermed_results);

        table.addView(getRow("1. Meantime", personResult.getLap1().getMeantime1()));
        table.addView(getRow("2. Meantime", personResult.getLap1().getMeantime2()));
        table.addView(getRow("3. Meantime", personResult.getLap1().getMeantime3()));
        table.addView(getResultRow("Total", personResult.getLap1().getTotal()));

    }


    private TableRow getRow(String caption, Result result){

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
        field.setText(result.getTime());
        field.setTextSize(13);
        field.setHeight(90);
        field.setWidth(200);
        field.setTextColor(textColor);

        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(result.getRelativeToBest());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(result.getRelativeToMe());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;
    }


    private TableRow getResultRow(String caption, Result result){

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
        field.setText(result.getTime());
        field.setTextSize(13);
        field.setHeight(90);
        field.setWidth(200);
        field.setTextColor(primaryColor);

        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(result.getRelativeToBest());
        field.setTextSize(13);
        field.setTextColor(primaryColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(result.getRelativeToMe());
        field.setTextSize(13);
        field.setTextColor(primaryColor);
        field.setWidth(150);
        field.setGravity(Gravity.END);
        row.addView(field);

        return row;

    }
}
