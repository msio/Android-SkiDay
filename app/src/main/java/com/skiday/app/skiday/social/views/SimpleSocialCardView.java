package com.skiday.app.skiday.social.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;

import com.skiday.app.skiday.R;


public class SimpleSocialCardView extends AbstractSocialCardView {
    protected Button facebookButton;
    protected Button twitterButton;
    protected Button commentsButton;
    protected TypedArray attributes;

    public SimpleSocialCardView(Context context, int layoutRef) {
        super(context, layoutRef);
    }

    public SimpleSocialCardView(Context context, @Nullable AttributeSet attrs, int layoutRef) {
        super(context, attrs, layoutRef);
    }

    public SimpleSocialCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int layoutRef) {
        super(context, attrs, defStyleAttr, layoutRef);
    }

    @Override
    protected void initializeCardView(Context context, AttributeSet attrs, int layoutRef) {
        inflate(context, layoutRef, this);

        this.facebookButton = (Button)this.findViewById(R.id.facebook_button);
        this.twitterButton = (Button)this.findViewById(R.id.twitter_button);
        this.commentsButton = (Button)this.findViewById(R.id.comments_button);

        this.attributes = context.obtainStyledAttributes(attrs, R.styleable.SimpleSocialCardView);

        assert(this.facebookButton != null);
        assert(this.twitterButton != null);
        assert(this.commentsButton != null);

        this.facebookButton.setText(this.attributes.getInt(R.styleable.SimpleSocialCardView_facebookLikes, 0) + "");
        this.twitterButton.setText(this.attributes.getInt(R.styleable.SimpleSocialCardView_twitterLikes, 0) + "");
        this.commentsButton.setText(this.attributes.getInt(R.styleable.SimpleSocialCardView_numberOfComments, 0) + "");
    }
}
