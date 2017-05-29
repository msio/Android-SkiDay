package com.skiday.app.skiday.ranking;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;
import com.skiday.app.skiday.constants.NavigationTab;
import com.skiday.app.skiday.model.MeantimeResultLine;
import com.skiday.app.skiday.model.Person;
import com.skiday.app.skiday.model.PersonResult;
import com.skiday.app.skiday.model.Results;

/**
 * Show details of a run.
 * <p>
 * Created by jan on 03.05.17.
 */
public class RankDetails extends AppCompatActivity {

    private static final String TAG = "RankingDetailFragment";

    private int id;
    private int lapNumber;
    private Person person;
    private boolean live = false;

    private MeantimeResultLine meanTime1;
    private MeantimeResultLine meanTime2;
    private MeantimeResultLine meanTime3;
    private MeantimeResultLine meanTime4;

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.navigationTab = NavigationTab.RANKING;
        setContentView(R.layout.rank_detail);
        Intent intent = getIntent();

        id = intent.getExtras().getInt("rankId");
        lapNumber = intent.getExtras().getInt("rankLapNumber");
        live = intent.getExtras().getBoolean("live");

        person = Results.getResults().getPersons().get(id);

        TextView field = (TextView) findViewById(R.id.name);
        field.setText(person.getName());

        field = (TextView) findViewById(R.id.rank_detail_lap);
        field.setText(lapNumber + ". Lap");

        MeantimeResultLine result3 = Results.getResults().getMeantimeResultLine(id, lapNumber, 4);
        field = (TextView) findViewById(R.id.current_time);
        field.setText(PersonResult.timeToString(result3.getTime()));

        ImageView profile = (ImageView) findViewById(R.id.profile_image);
        Drawable drawable = getResources().getDrawable(person.getPictureId());
        profile.setImageDrawable(drawable);


        meanTime1 = Results.getResults().getMeantimeResultLine(id, lapNumber, 1);
        meanTime2 = Results.getResults().getMeantimeResultLine(id, lapNumber, 2);
        meanTime3 = Results.getResults().getMeantimeResultLine(id, lapNumber, 3);
        meanTime4 = Results.getResults().getMeantimeResultLine(id, lapNumber, 4);

        if (live) {
            startLive();
        } else {
            showResult();
        }
    }

    public void showResult() {
        fillMeantimeView(1, meanTime1);
        fillMeantimeView(2, meanTime2);
        fillMeantimeView(3, meanTime3);
        fillMeantimeView(4, meanTime4);
    }

    private void fillMeantimeView(int meanTime, MeantimeResultLine result) {

        int time = 0, best = 0, me = 0;

        switch (meanTime) {
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

        TextView field = (TextView) findViewById(time);
        field.setText(PersonResult.timeToString(result.getTime()));

        field = (TextView) findViewById(best);
        field.setText(PersonResult.timeToString(result.getRelBest()));

        field = (TextView) findViewById(me);
        field.setText(PersonResult.timeToString(result.getRelMe()));
    }

    /**
     * Start live-move.
     */
    public void startLive() {

        final int millis = meanTime4.getTime() * 10;

        final int mTime1 = meanTime1.getTime();
        final int mTime2 = meanTime2.getTime();
        final int mTime3 = meanTime3.getTime();

        Log.i(TAG, "startLive: " + mTime1);

        currentTime = (TextView) findViewById(R.id.current_time);

        CountDownTimer timer = new CountDownTimer(millis, 10) {
            boolean mt1 = false, mt2 = false, mt3 = false;

            @Override
            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished / 10;
                long countUp = (millis / 10) - seconds;
                setClock(countUp);

                long millisCounting = millis - millisUntilFinished;

                if (!mt1 && (millisCounting > (mTime1 * 10))) {
                    // show Meantime1
                    fillMeantimeView(1, meanTime1);
                    Log.i(TAG, "Show meantime 1");
                    mt1 = true;
                }

                if (!mt2 && (millisCounting > (mTime2 * 10))) {
                    // show Meantime 2
                    fillMeantimeView(2, meanTime2);
                    Log.i(TAG, "Show meantime 2");
                    mt2 = true;
                }

                if (!mt3 && (millisCounting > (mTime3 * 10))) {
                    // show Meantime 3
                    fillMeantimeView(3, meanTime3);
                    Log.i(TAG, "Show meantime 3");
                    mt3 = true;
                }
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: ");
                fillMeantimeView(4, meanTime4);
                setClock(millis / 10);
            }
        };
        timer.start();
    }


    TextView currentTime = null;

    /**
     * Setting the clock-view to the value.
     *
     * @param millis
     */
    public void setClock(long millis) {
        currentTime.setText(PersonResult.timeToString2((int) millis));
    }

}
