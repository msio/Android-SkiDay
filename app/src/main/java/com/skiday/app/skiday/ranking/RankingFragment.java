package com.skiday.app.skiday.ranking;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.model.Lap;
import com.skiday.app.skiday.model.MeantimeResultLine;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Result;
import com.skiday.app.skiday.model.Results;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.timeline.TimelineFragment;

import java.util.ArrayList;
import java.util.List;

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

    public void setupTable(View view){

        TableLayout table = (TableLayout) view.findViewById(R.id.ranking_table_lap1);
        TableLayout table1 = (TableLayout) view.findViewById(R.id.ranking_table_lap2);
        TableLayout table2 = (TableLayout) view.findViewById(R.id.ranking_table_result);

        int rank = 1;
        for (MeantimeResultLine line : getResults ().getLapResultTable(1)){
            table.addView(getRow(line, rank, 1));
            rank++;
        }
        rank = 1;
        for (MeantimeResultLine line : getResults ().getLapResultTable(2)){
            table1.addView(getRow(line, rank, 2));
            rank++;
        }
        rank = 1;
        for (MeantimeResultLine line : getResults ().getLapResultTable(3)){
            table2.addView(getRow(line, rank, 3));
            rank++;
        }
    }

    private View getRow(MeantimeResultLine line, int rank, int lapNumber){

        Log.i(TAG, "getRow: "+line);

        View listItemView = mInflater.inflate(R.layout.rank_list_item, null);
        listItemView.setId(line.getPerson().getId());
        listItemView.setTag(R.integer.lapNumber, lapNumber);
        listItemView.setOnClickListener(this);

        TextView view = (TextView)listItemView.findViewById(R.id.rank_list_item_name);
        view.setText(line.getPerson().getName());

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rank);
        view.setText(rank + ".");

        ImageView imgView = (ImageView)listItemView.findViewById(R.id.rank_list_item_profile_image);
        Drawable drawable = getResources().getDrawable(line.getPerson().getPictureId());
        imgView.setImageDrawable(drawable);

        imgView = (ImageView)listItemView.findViewById(R.id.rank_list_item_profile_best_icon);

        if(line.getRelBest() != 0)
            imgView.setImageDrawable(null);

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_time);
        view.setText(PersonResult.timeToString(line.getTime()));

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rel_best);
        view.setText(PersonResult.timeToString(line.getRelBest()));

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rel_me);
        view.setText(PersonResult.timeToString(line.getRelMe()));

        return listItemView;
    }


    private View getRow(PersonResult data, Result result){

        View listItemView = mInflater.inflate(R.layout.rank_list_item, null);
        listItemView.setId(data.getRank());
        listItemView.setOnClickListener(this);

        TextView view = (TextView)listItemView.findViewById(R.id.rank_list_item_name);
        view.setText(data.getName());

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rank);
        view.setText(data.getRank() + ".");

        ImageView imgView = (ImageView)listItemView.findViewById(R.id.rank_list_item_profile_image);
        Drawable drawable = getResources().getDrawable(data.getPictureId());
        imgView.setImageDrawable(drawable);

        imgView = (ImageView)listItemView.findViewById(R.id.rank_list_item_profile_best_icon);
        imgView.setImageDrawable(null);

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_time);
        view.setText(result.getTime());

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rel_best);
        view.setText(result.getRelativeToBest());

        view = (TextView)listItemView.findViewById(R.id.rank_list_item_rel_me);
        view.setText(result.getRelativeToMe());

        return listItemView;
    }

    private View getRow1(PersonResult data, Result result){


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

        int lapNumber = (int) v.getTag(R.integer.lapNumber);

        if (lapNumber == 3) return;

        Fragment fragment = RankDetailFragment.newInstance(v.getId(), lapNumber);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack("rankDetail").commit();
    }

    static Results results = null;
    public static Results getResults(){
        if (results == null) {
            results = new Results();
            results.init();
        }
        return results;
    }

    public static List<PersonResult> getData1(){

        List<PersonResult> rankData = new ArrayList<>();

        PersonResult personResult = new PersonResult("Marcel Hirscher");
        personResult.setPictureId(R.mipmap.hirscher);
        personResult.setRank(1);
        Lap lap = new Lap();
        lap.setMeantime1(new Result(1523, 120, -210));
        lap.setMeantime2(new Result(3510, 050, -120));
        lap.setMeantime3(new Result(6112, 10, -130));
        lap.setTotal(new Result(7985, 0, 130));
        personResult.setLap1(lap);

        lap = new Lap();
//        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
//        lap.setMeantime2(new Result("00:34.32", "+0.4", "-1.3"));
//        lap.setMeantime3(new Result("01:04,14", "+0,2", "-1.3"));
//        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

//        personResult.setTotal(new Result("02:40,85", "0", "+1.3"));
        rankData.add(personResult);


        personResult = new PersonResult("Hermann Maier");
        personResult.setPictureId(R.mipmap.maier);
        personResult.setRank(2);
        lap = new Lap();
//        lap.setMeantime1(new Result("00:15.23", "+1.2", "-2.1"));
//        lap.setMeantime2(new Result("00:35.10", "+0.5", "-1.2"));
//        lap.setMeantime3(new Result("01:01,12", "+0,1", "-1.3"));
//        lap.setTotal(new Result("01:19,85", "0", "+1.3"));
        personResult.setLap1(lap);

        lap = new Lap();
//        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
//        lap.setMeantime2(new Result("00:34.32", "+0.4", "-1.3"));
//        lap.setMeantime3(new Result("01:04,14", "+0,2", "-1.3"));
//        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

//        personResult.setTotal(new Result("02:41,82", "+1", "+0.3"));
        rankData.add(personResult);


        personResult = new PersonResult("H. Kristoffersen");
        personResult.setPictureId(R.mipmap.kristoffersen);
        personResult.setRank(3);
        lap = new Lap();
//        lap.setMeantime1(new Result("00:15.23", "+1.2", "-2.1"));
//        lap.setMeantime2(new Result("00:35.10", "+0.5", "-1.2"));
//        lap.setMeantime3(new Result("01:01,12", "+0,1", "-1.3"));
//        lap.setTotal(new Result("01:19,85", "0", "+1.3"));
        personResult.setLap1(lap);

        lap = new Lap();
//        lap.setMeantime1(new Result("00:14.23", "+1.2", "-2.1"));
//        lap.setMeantime2(new Result("00:33.32", "+0.4", "-1.3"));
//        lap.setMeantime3(new Result("01:05,23", "+0,2", "-1.3"));
//        lap.setTotal(new Result("01:21,75", "0", "+1.4"));
        personResult.setLap2(lap);

//        personResult.setTotal(new Result("02:42,15", "2.1", "0"));
        rankData.add(personResult);

        return rankData;
    }
}
