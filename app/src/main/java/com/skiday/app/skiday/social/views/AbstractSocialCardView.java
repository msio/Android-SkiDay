package com.skiday.app.skiday.social.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public abstract class AbstractSocialCardView extends LinearLayout {
    public AbstractSocialCardView(Context context, int layoutRef) {
        super(context);
        initializeCardView(context, null, layoutRef);
    }

    public AbstractSocialCardView(Context context, @Nullable AttributeSet attrs, int layoutRef) {
        super(context, attrs);
        initializeCardView(context, attrs, layoutRef);
    }

    public AbstractSocialCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int layoutRef) {
        super(context, attrs, defStyleAttr);
        initializeCardView(context, null, layoutRef);
    }

    abstract void initializeCardView(Context context, AttributeSet attrs, int layoutRef);
}
