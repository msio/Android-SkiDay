package com.skiday.app.skiday.ranking;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.model.Lap;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Result;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.timeline.TimelineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msio on 4/27/17.
 */

public class RankingFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "RankingFragment";

    private String [][] rankDataLap1 ={
            {"1.", "Marcel Hirscher", "1:19:85", "0", "0.3"},
            {"2.", "H. Kristoffersen", "1:20:10", "+0.1", "-0.2"},
            {"3.", "Hermann Maier", "1:22:85", "+0.3", "0"},
    };

    private String [][] rankDataLap2 ={
            {"1.", "H. Kristoffersen", "1:20:84", "0", "-2.3"},
            {"2.", "Marcel Hirscher", "1:21:11", "+0.1", "-1.2"},
            {"3.", "Hermann Maier", "1:22:85", "+2.3", "0"},
    };

    private String [][] rankDataTotal ={
            {"1.", "Marcel Hirscher", "2:40:15", "0", "0.3"},
            {"2.", "H. Kristoffersen", "2:43:13", "+0.1", "-0.2"},
            {"3.", "Hermann Maier", "2:44:85", "+0.3", "0"},
    };

    private String [][] resultTable = {
            {"00:15.23", "+1.2", "-2.1"},
            {"00:35.10", "+0.5", "-1.2"},
            {"01:01,12", "+0,1", "-1.3"},
            {"01:19,85", "0", "+1.3"},
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

        spec = host.newTabSpec("Total");
        spec.setContent(R.id.tab3Ranking);
        spec.setIndicator("Total");
        host.addTab(spec);

        setupTable(view);

    }

    public void setupTable(View view){

        TableLayout table = (TableLayout) view.findViewById(R.id.ranking_table_lap1);
        TableLayout table1 = (TableLayout) view.findViewById(R.id.ranking_table_lap2);
        TableLayout table2 = (TableLayout) view.findViewById(R.id.ranking_table_result);

  /*      for (int i = 0; i< rankDataLap1.length; i++){

            table.addView(getRow(rankDataLap1, i));
            table1.addView(getRow(rankDataLap2, i));
            table2.addView(getRow(rankDataTotal, i));
        }*/

        for(PersonResult person : getData()){
            table.addView(getRow(person, person.getLap1().getTotal()));
            table1.addView(getRow(person, person.getLap2().getTotal()));
            table2.addView(getRow(person, person.getTotal()));
        }

    }

    private TableRow getRow(PersonResult data, Result result){

        int textColor = getResources().getColor(R.color.colorRankTableFieldText);

        TableRow row = new TableRow(getContext());
        row.setId(data.getRank());
        row.setMinimumHeight(200);
        row.setGravity(Gravity.CENTER_VERTICAL);


        row.setOnClickListener(this);

        // Rank
        TextView field = new TextView(getContext());
        field.setText(""+data.getRank());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setHeight(90);
        row.addView(field);

        // Picture
//                                    <ImageView
//        android:id="@+id/imageView2"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        app:srcCompat="@mipmap/hirscher" />

        ImageView profilePicture = new ImageView(getContext());
        Drawable drawable = getResources().getDrawable(data.getPictureId());
        profilePicture.setImageDrawable(drawable);
        profilePicture.setPadding(20, 0, 20, 0);
        row.addView(profilePicture);

        // Name
        field = new TextView(getContext());
        field.setText(data.getName());
        field.setTextSize(13);
        field.setHeight(90);
        field.setTextColor(textColor);

        row.addView(field);

        // Time
        field = new TextView(getContext());
        field.setText(result.getTime());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setHeight(90);
        row.addView(field);

        // Time in relation to best
        field = new TextView(getContext());
        field.setText(result.getRelativeToBest());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(90);
        field.setGravity(Gravity.CENTER_VERTICAL | Gravity.END );
        field.setPadding(0,0,0,30);
        row.addView(field);

        // Time in relation to "me"
        field = new TextView(getContext());
        field.setText(result.getRelativeToMe());
        field.setTextSize(13);
        field.setTextColor(textColor);
        field.setWidth(50);
        field.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        field.setPadding(0,0,0,30);
        row.addView(field);

        return row;
    }

    @Override
    public void onClick(View v) {

        Log.i(TAG, "onClick: "+v.getId());

        Fragment fragment = RankDetailFragment.newInstance(v.getId());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack("rankDetail").commit();
    }

    public static List<PersonResult> getData(){

        List<PersonResult> rankData = new ArrayList<>();

        PersonResult personResult = new PersonResult("Marcel Hirscher");
        personResult.setPictureId(R.mipmap.hirscher);
        personResult.setRank(1);
        Lap lap = new Lap();
        lap.setMeantime1(new Result("00:15.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:35.10", "+0.5", "-1.2"));
        lap.setMeantime3(new Result("01:01,12", "+0,1", "-1.3"));
        lap.setTotal(new Result("01:19,85", "0", "+1.3"));
        personResult.setLap1(lap);

        lap = new Lap();
        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:34.32", "+0.4", "-1.3"));
        lap.setMeantime3(new Result("01:04,14", "+0,2", "-1.3"));
        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

        personResult.setTotal(new Result("02:40,85", "0", "+1.3"));
        rankData.add(personResult);


        personResult = new PersonResult("Hermann Maier");
        personResult.setPictureId(R.mipmap.maier);
        personResult.setRank(2);
        lap = new Lap();
        lap.setMeantime1(new Result("00:15.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:35.10", "+0.5", "-1.2"));
        lap.setMeantime3(new Result("01:01,12", "+0,1", "-1.3"));
        lap.setTotal(new Result("01:19,85", "0", "+1.3"));
        personResult.setLap1(lap);

        lap = new Lap();
        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:34.32", "+0.4", "-1.3"));
        lap.setMeantime3(new Result("01:04,14", "+0,2", "-1.3"));
        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

        personResult.setTotal(new Result("02:41,82", "+1", "+0.3"));
        rankData.add(personResult);


        personResult = new PersonResult("H. Kristoffersen");
        personResult.setPictureId(R.mipmap.kristoffersen);
        personResult.setRank(3);
        lap = new Lap();
        lap.setMeantime1(new Result("00:15.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:35.10", "+0.5", "-1.2"));
        lap.setMeantime3(new Result("01:01,12", "+0,1", "-1.3"));
        lap.setTotal(new Result("01:19,85", "0", "+1.3"));
        personResult.setLap1(lap);

        lap = new Lap();
        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
        lap.setMeantime2(new Result("00:33.32", "+0.4", "-1.3"));
        lap.setMeantime3(new Result("01:05,23", "+0,2", "-1.3"));
        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

        personResult.setTotal(new Result("02:42,15", "2.1", "0"));
        rankData.add(personResult);

        return rankData;
    }
}
