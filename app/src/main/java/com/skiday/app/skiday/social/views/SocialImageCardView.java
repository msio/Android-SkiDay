package com.skiday.app.skiday.social.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;

import com.skiday.app.skiday.R;


public class SocialImageCardView extends SimpleSocialCardView {
    protected Button instagramButton;
    protected ImageView image;

    public SocialImageCardView(Context context) {
        super(context,  R.layout.social_image_card);
    }

    public SocialImageCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,  R.layout.social_image_card);
    }

    public SocialImageCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr,  R.layout.social_image_card);


    }

    @Override
    protected void initializeCardView(Context context, AttributeSet attrs, int layoutRef){
        super.initializeCardView(context, attrs, layoutRef);

        this.instagramButton = (Button)findViewById(R.id.instagram_button);
        assert(this.instagramButton != null);

        this.image = (ImageView) findViewById(R.id.image);
        assert(this.image != null);

        TypedArray additionalAttributes = context.obtainStyledAttributes(attrs, R.styleable.SocialImageCardView);


        this.instagramButton.setText(additionalAttributes.getInt(R.styleable.SocialImageCardView_instagramLikes, 0) + " Likes");
        this.image.setImageResource(additionalAttributes.getResourceId(R.styleable.SocialImageCardView_imageReference, R.drawable.example_selfie));
    }
}
