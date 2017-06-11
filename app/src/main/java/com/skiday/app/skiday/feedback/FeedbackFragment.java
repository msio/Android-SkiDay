package com.skiday.app.skiday.feedback;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.social.SocialFragment;

public class FeedbackFragment extends Fragment implements View.OnClickListener{
    private FloatingActionButton floatingActionButton;

    public static FeedbackFragment newInstance(){
        FeedbackFragment fragment = new FeedbackFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Feedback", "Create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Feedback", "CreateView");
        return inflater.inflate(R.layout.fragment_social_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("Feedback", "ViewCreated");
        if(savedInstanceState != null){
            Log.i("Feedback", savedInstanceState.getString("Key"));
        }
        super.onViewCreated(view, savedInstanceState);
        this.floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab_create_social_post);
        this.floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, SocialFragment.newInstance()).addToBackStack("Feedback");
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Feedback", "Resume");
    }

    public void test(){
        Log.i("GTESTING", "HELLOE");
    }
}