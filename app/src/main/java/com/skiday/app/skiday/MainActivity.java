package com.skiday.app.skiday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.skiday.app.skiday.constants.NavigationTab;
import com.skiday.app.skiday.feedback.FeedbackFragment;
import com.skiday.app.skiday.login.LoginActivity;
import com.skiday.app.skiday.model.Results;
import com.skiday.app.skiday.ranking.RankDetails;
import com.skiday.app.skiday.ranking.RankingFragment;
import com.skiday.app.skiday.settings.SettingsFragment;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.timeline.TimelineFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static int ownId = 1; // Marcel Hirscher is the current user
    public static NavigationTab navigationTab = NavigationTab.TIMELINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_live);
        fab.setOnClickListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(navigation);

        if (savedInstanceState != null) {
            System.out.println("DETAILS " + savedInstanceState.getString("details"));
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (navigationTab == NavigationTab.RANKING) {
            transaction.replace(R.id.content, RankingFragment.newInstance());
            navigation.setSelectedItemId(R.id.menu_ranking);
        } else if (navigationTab == NavigationTab.TIMELINE) {
            transaction.replace(R.id.content, TimelineFragment.newInstance());
            navigation.setSelectedItemId(R.id.menu_timeline);
        }
        transaction.commit();
    }

    /**
     * shows always icon with title in bottom navigation
     *
     * @param view
     */
    private static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.menu_timeline:
                    selectedFragment = TimelineFragment.newInstance();
                    break;
                case R.id.menu_social:
                    selectedFragment = SocialFragment.newInstance();
                    break;
                case R.id.menu_ranking:
                    Log.i(TAG, "ranking selected");
                    selectedFragment = RankingFragment.newInstance();
                    break;
                case R.id.menu_feedback:
                    selectedFragment = FeedbackFragment.newInstance();
            }
            loadFragment(selectedFragment);
            return true;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    public void feedbackButtonOnClick(View view) {
        String url;
        switch (view.getId()) {
            case R.id.twitter_button:
                url = "https://twitter.com/";
                break;
            case R.id.facebook_button:
                url = "https://facebook.com/";
                break;
            case R.id.instagram_button:
                url = "http://instagram.com/";
                break;
            default:
                url = "https://google.de/";
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_button:

                Intent Intent = new Intent(this, LoginActivity.class);
                startActivity(Intent);
                break;
            case R.id.settings_button:
                loadFragment(new SettingsFragment());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public static int getOwnId() {
        return ownId;
    }

    @Override
    public void onClick(View v) {

        Log.i(TAG, "onClick: Fab clicked.");

        int count = Results.getResults().getPersons().size();

        int id = (int) (Math.random() * 10) % count;

        Intent intent = new Intent(this, RankDetails.class);
        intent.putExtra("rankId", id);
        intent.putExtra("rankLapNumber", 2);
        intent.putExtra("live", true);
        startActivity(intent);


    }
}
