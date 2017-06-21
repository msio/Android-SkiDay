package com.skiday.app.skiday.social.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skiday.app.skiday.R;
import com.skiday.app.skiday.dto.SocialMediaAttachment;

import java.io.File;


public class SocialImageCardView extends SimpleSocialCardView {
    protected Button instagramButton;
    protected ImageView image;
    protected TextView geolocation;

    public SocialImageCardView(Context context) {
        super(context, R.layout.social_image_card);
    }

    public SocialImageCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, R.layout.social_image_card);
    }

    public SocialImageCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, R.layout.social_image_card);


    }

    @Override
    protected void initializeCardView(Context context, AttributeSet attrs, int layoutRef) {
        super.initializeCardView(context, attrs, layoutRef);

        this.instagramButton = (Button) findViewById(R.id.instagram_button);
        assert (this.instagramButton != null);

        this.image = (ImageView) findViewById(R.id.post_image);
        assert (this.image != null);

        TypedArray additionalAttributes = context.obtainStyledAttributes(attrs, R.styleable.SocialImageCardView);

        this.geolocation = (TextView) findViewById(R.id.geolocation);

        this.instagramButton.setText(additionalAttributes.getInt(R.styleable.SocialImageCardView_instagramLikes, 0) + "");
        this.image.setImageResource(additionalAttributes.getResourceId(R.styleable.SocialImageCardView_imageReference, R.drawable.example_selfie));
    }

    public void setImageByPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            Log.wtf("CardView", "Image not found");
        }
        Bitmap image = BitmapFactory.decodeFile(path);
        this.image.setImageBitmap(image);
    }

    public void setInstagramLikes(String likes) {
        this.instagramButton.setText(likes);
    }

    public void setGeolocation(String geo, int resource) {
        this.geolocation.setText(geo+", ");
    }
}
