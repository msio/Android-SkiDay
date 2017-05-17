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
public class RankDetailFragment extends Fragment {

    private static final String TAG = "RankingDetailFragment";

    private int id;
    private int lapNumber;
    private Person person;
    private boolean live = false;

    public static RankDetailFragment newInstance(int id, int lapNumber, boolean live) {
        Log.i(TAG, "newInstance: "+id);
        RankDetailFragment fragment = new RankDetailFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: "+id);
        person = Results.getResults().getPersons().get(id);

        return inflater.inflate(R.layout.fragment_rank_detail, container, false);
    }

    MeantimeResultLine meanTime1;
    MeantimeResultLine meanTime2;
    MeantimeResultLine meanTime3;
    MeantimeResultLine meanTime4;

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

    private void fillMeantimeView(View view, int meanTime, MeantimeResultLine result){

        int time = 0, best = 0, me = 0;

        switch (meanTime){
            case 1:
                time = R.id.rank_detail_m1_time;
                best = R.id.rank_detail_m1_best;
                me = R.id.rank_detail_m1_me;
                break;
            case 2:
                time = R.id.rank_detail_m2_time;
                best = R.id.rank_detail_m2_best;
                me = R.id.rank_detail_m2_me;
                break;
            case 3:
                time = R.id.rank_detail_m3_time;
                best = R.id.rank_detail_m3_best;
                me = R.id.rank_detail_m3_me;
                break;
            case 4:
                time = R.id.rank_detail_m4_time;
                best = R.id.rank_detail_m4_best;
                me = R.id.rank_detail_m4_me;
                break;
        }

        TextView field = (TextView) view.findViewById(time);
        field.setText(PersonResult.timeToString(result.getTime()));

        field = (TextView) view.findViewById(best);
        field.setText(PersonResult.timeToString(result.getRelBest()));

        field = (TextView) view.findViewById(me);
        field.setText(PersonResult.timeToString(result.getRelMe()));
    }

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

        CountDownTimer timer = new CountDownTimer(millis, 10) {
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
