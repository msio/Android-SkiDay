package com.skiday.app.skiday.settings;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;

import com.skiday.app.skiday.R;

/**
 * Created by msio on 6/10/17.
 */

public class UserProfilePreference extends Preference {

    public UserProfilePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserProfilePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWidgetLayoutResource(R.layout.activity_settings);
    }
}
