package com.skiday.app.skiday.feedback;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skiday.app.skiday.MainActivity;
import com.skiday.app.skiday.R;
import com.skiday.app.skiday.dao.ISocialMediaDAO;
import com.skiday.app.skiday.dao.SocialMediaDAO;
import com.skiday.app.skiday.dao.SocialMediaPostContract;
import com.skiday.app.skiday.dto.SocialMediaAttachment;
import com.skiday.app.skiday.dto.SocialMediaPostDTO;
import com.skiday.app.skiday.social.SocialFragment;
import com.skiday.app.skiday.social.views.AbstractSocialCardView;
import com.skiday.app.skiday.social.views.SimpleSocialCardView;
import com.skiday.app.skiday.social.views.SocialImageCardView;
import com.skiday.app.skiday.social.views.SocialTextCardView;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.Collections;
import java.util.List;

public class FeedbackFragment extends Fragment implements View.OnClickListener{
    private FloatingActionButton floatingActionButton;
    private ISocialMediaDAO socialMediaDAO;
    private LinearLayout layout;

    public static FeedbackFragment newInstance(ISocialMediaDAO socialMediaDAO){
        FeedbackFragment fragment = new FeedbackFragment();
        fragment.setSocialMediaDAO(socialMediaDAO);
        return fragment;
    }

    public ISocialMediaDAO getSocialMediaDAO() {
        return socialMediaDAO;
    }

    public void setSocialMediaDAO(ISocialMediaDAO socialMediaDAO) {
        this.socialMediaDAO = socialMediaDAO;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Social");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Feedback", "CreateView");
        return inflater.inflate(R.layout.fragment_social_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("Feedback", "ViewCreated");

        super.onViewCreated(view, savedInstanceState);
        this.floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab_create_social_post);
        this.layout = (LinearLayout) view.findViewById(R.id.feedback_layout);
        this.floatingActionButton.setOnClickListener(this);

        this.loadSocialMediaPostsIntoView();

    }

    private void loadSocialMediaPostsIntoView(){
        if(this.getSocialMediaDAO() == null)
            return;

        List<SocialMediaPostDTO> posts = this.getSocialMediaDAO().getAllSocialMediaPosts();
        for(SocialMediaPostDTO post : posts){
            SimpleSocialCardView cardView = null;
            SocialMediaAttachment attachment = post.getSocialMediaAttachment();
            if(attachment == null || attachment.getReference() == null){
                SocialTextCardView textCardView = new SocialTextCardView(getContext());
                textCardView.setPostText(post.getText());
                cardView = textCardView;
            }else{
                SocialImageCardView imageCardView = new SocialImageCardView(getContext());
                imageCardView.setImageByPath(attachment.getReference());
                if(post.getGeoLocation().equals(SocialFragment.DEBUGING_EXAMPLE_LOCATION)){
                    imageCardView.setGeolocation("Kitzbühl", R.drawable.aus);
                }
                imageCardView.setPostText(post.getText());
                cardView = imageCardView;
            }

            cardView.setTimestamp(post.getTimestamp());



            if(cardView != null)
                this.layout.addView(cardView, 0);
        }
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, SocialFragment.newInstance(this.socialMediaDAO)).addToBackStack("Feedback");
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