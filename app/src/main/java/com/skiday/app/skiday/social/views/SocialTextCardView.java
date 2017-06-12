package com.skiday.app.skiday.social.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.skiday.app.skiday.R;

public class SocialTextCardView extends SimpleSocialCardView {

    public SocialTextCardView(Context context) {
        super(context, R.layout.social_text_card);
    }

    public SocialTextCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.layout.social_text_card);
    }

    public SocialTextCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.social_text_card);
    }

    @Override
    protected void initializeCardView(Context context, AttributeSet attrs, int layoutRef){
        super.initializeCardView(context, attrs, R.layout.social_text_card);

        TypedArray additionalArguemnts = context.obtainStyledAttributes(attrs, R.styleable.SocialTextCardView);
        String textPost = additionalArguemnts.getString(R.styleable.SocialTextCardView_textPost);

        if(textPost == null || textPost.trim().isEmpty()){
            textPost = "Boah, heute war es mal kalt auf der Piste!";
        }

        textPost = textPost.trim();

        setPostText(textPost);
    }
}
