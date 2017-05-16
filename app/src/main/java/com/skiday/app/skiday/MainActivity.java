package com.skiday.app.skiday;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.skiday.app.skiday.feedback.FeedbackFragment;
import com.skiday.app.skiday.login.LoginActivity;
import com.skiday.app.skiday.ranking.RankingFragment;
import com.skiday.app.skiday.settings.SettingsFragment;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.timeline.TimelineFragment;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static int ownId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,TimelineFragment.newInstance());
        transaction.commit();
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

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    public void feedbackButtonOnClick(View view){
        String url;
        switch(view.getId()){
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
        switch(item.getItemId()){
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

    public static int getOwnId(){
        return ownId;
    }
}
