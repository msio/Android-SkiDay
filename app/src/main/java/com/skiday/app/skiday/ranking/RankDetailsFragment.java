package com.skiday.app.skiday.ranking;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;
import com.skiday.app.skiday.model.MeantimeResultLine;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Results;

/**
 * Show details of a run.
 *
 * Created by jan on 03.05.17.
 */
public class RankDetailsFragment extends Fragment {

    private static final String TAG = "RankingDetailFragment";

    private int id;
    private int lapNumber;
    private Person person;
    private boolean live = false;

    MeantimeResultLine meanTime1;
    MeantimeResultLine meanTime2;
    MeantimeResultLine meanTime3;
    MeantimeResultLine meanTime4;

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach: stop the clock");
        timer.cancel();
        super.onDetach();
    }

    public static RankDetailsFragment newInstance(int id, int lapNumber, boolean live) {
        Log.i(TAG, "newInstance: "+id);
        RankDetailsFragment fragment = new RankDetailsFragment();
        fragment.setId(id);
        fragment.setLapNumber(lapNumber);
        fragment.setLive(live);
        return fragment;
    }

    public void setLive(boolean live){
        this.live = live;
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
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Live");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: "+id);
        person = Results.getResults().getPersons().get(id);

        if (person == null) {
            person = Results.getResults().getPersons().get(1);
        }

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

        MeantimeResultLine result3 = Results.getResults().getMeantimeResultLine(id, lapNumber, 4);
        field = (TextView) view.findViewById (R.id.current_time);
        field.setText(PersonResult.timeToString(result3.getTime()));

        ImageView profile = (ImageView) view.findViewById (R.id.profile_image);
        Drawable drawable = getResources().getDrawable(person.getPictureId());
        profile.setImageDrawable(drawable);


        meanTime1 = Results.getResults().getMeantimeResultLine(id, lapNumber, 1);
        meanTime2 = Results.getResults().getMeantimeResultLine(id, lapNumber, 2);
        meanTime3 = Results.getResults().getMeantimeResultLine(id, lapNumber, 3);
        meanTime4 = Results.getResults().getMeantimeResultLine(id, lapNumber, 4);

        if (live)
            startLive(view);
        else
            showResult(view);
    }

    public void showResult(View view){
        fillMeantimeView(view, 1, meanTime1);
        fillMeantimeView(view, 2, meanTime2);
        fillMeantimeView(view, 3, meanTime3);
        fillMeantimeView(view, 4, meanTime4);
    }

    private void fillMeantimeView(View view,int meanTime, MeantimeResultLine result) {

        int time = 0, absBest = 0, relBest = 0, absMe = 0, relMe = 0;

        Log.i(TAG, "fillMeantimeView: meantime: "+meanTime);

        switch (meanTime) {
            case 1:
                time = R.id.rank_detail_m1_time;
                absBest = R.id.rank_detail_m1_best_abs;
                absMe = R.id.rank_detail_m1_me_abs;
                relBest = R.id.rank_detail_m1_best_rel;
                relMe = R.id.rank_detail_m1_me_rel;
                break;
            case 2:
                time = R.id.rank_detail_m2_time;
                absBest = R.id.rank_detail_m2_best_abs;
                absMe = R.id.rank_detail_m2_me_abs;
                relBest = R.id.rank_detail_m2_best_rel;
                relMe = R.id.rank_detail_m2_me_rel;
                break;
            case 3:
                time = R.id.rank_detail_m3_time;
                absBest = R.id.rank_detail_m3_best_abs;
                absMe = R.id.rank_detail_m3_me_abs;
                relBest = R.id.rank_detail_m3_best_rel;
                relMe = R.id.rank_detail_m3_me_rel;
                break;
            case 4:
                time = R.id.rank_detail_m4_time;
                absBest = R.id.rank_detail_m4_best_abs;
                absMe = R.id.rank_detail_m4_me_abs;
                relBest = R.id.rank_detail_m4_best_rel;
                relMe = R.id.rank_detail_m4_me_rel;
                break;
        }

        Log.i(TAG, "fillMeantimeView: "+time);
        TextView field = (TextView) view.findViewById(time);

        if (field == null){
            Log.e(TAG, "fillMeantimeView: field is null");
        }
        field.setText(PersonResult.timeToString(result.getTime()));

        field = (TextView) view.findViewById(relBest);
        field.setText(PersonResult.timeToRelativeString(result.getRelBest()));
        if (result.getRelBest() > 0)
            field.setTextColor(getResources().getColor(R.color.colorRankNegative));

        field = (TextView) view.findViewById(absBest);
        field.setText(PersonResult.timeToString(result.getAbsoluteTimeBest()));

        field = (TextView) view.findViewById(relMe);
        field.setText(PersonResult.timeToRelativeString(result.getRelMe()));
        if (result.getRelMe() > 0)
            field.setTextColor(getResources().getColor(R.color.colorRankNegative));

        field = (TextView) view.findViewById(absMe);
        field.setText(PersonResult.timeToString(result.getAbsoluteTimeMe()));

    }

    public CountDownTimer timer;

    /**
     * Start live-move.
     *
     * @param view
     */
    public void startLive(final View view){

        final int millis = meanTime4.getTime() * 10;

        final int mTime1 = meanTime1.getTime();
        final int mTime2 = meanTime2.getTime();
        final int mTime3 = meanTime3.getTime();

        Log.i(TAG, "startLive: "+mTime1);

        currentTime = (TextView) view.findViewById (R.id.current_time);

        timer = new CountDownTimer(millis, 10) {
            boolean mt1 = false, mt2 = false, mt3 = false;

            @Override
            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished/10;
                long countUp = (millis/10)-seconds;
                setClock(countUp);

                long millisCounting = millis - millisUntilFinished;

                if (!mt1 && (millisCounting > (mTime1*10))){
                    // show Meantime1
                    fillMeantimeView(view, 1, meanTime1);
                    Log.i(TAG, "Show meantime 1");
                    mt1 = true;
                }

                if (!mt2 && (millisCounting > (mTime2*10))){
                    // show Meantime 2
                    fillMeantimeView(view, 2, meanTime2);
                    Log.i(TAG, "Show meantime 2");
                    mt2 = true;
                }

                if (!mt3 && (millisCounting > (mTime3*10))){
                    // show Meantime 3
                    fillMeantimeView(view, 3, meanTime3);
                    Log.i(TAG, "Show meantime 3");
                    mt3 = true;
                }
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: ");
                fillMeantimeView(view, 4, meanTime4);
                setClock(millis/10);
            }
        };
        timer.start();
    }


    TextView currentTime = null;

    /**
     * Setting the clock-view to the value.
     * @param millis
     */
    public void setClock(long millis){
        currentTime.setText(PersonResult.timeToString2((int) millis));
    }
}