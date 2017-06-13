package com.skiday.app.skiday.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class SocialMediaDatabase implements Serializable{
    private SocialMediaDBHelper socialMediaDBHelper;

    private HashMap<String, Long> socialMediaServiceIDs;

    public SocialMediaDatabase(Context context) {
        this.socialMediaDBHelper = new SocialMediaDBHelper(context);
        SQLiteDatabase database = this.socialMediaDBHelper.getWritableDatabase();

        this.socialMediaServiceIDs = new HashMap<>();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_NAME, "Facebook");
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_FEEDBACK_STYLE, "Likes");
        long sID = database.insert(SocialMediaPostContract.SocialMediaService.TABLE_NAME, null, contentValues);
        this.socialMediaServiceIDs.put("facebook", sID);

        contentValues = new ContentValues();
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_NAME, "Instagram");
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_FEEDBACK_STYLE, "Likes");
        sID = database.insert(SocialMediaPostContract.SocialMediaService.TABLE_NAME, null, contentValues);
        this.socialMediaServiceIDs.put("instagram", sID);

        contentValues = new ContentValues();
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_NAME, "Twitter");
        contentValues.put(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_FEEDBACK_STYLE, "Retweets");
        sID = database.insert(SocialMediaPostContract.SocialMediaService.TABLE_NAME, null, contentValues);
        this.socialMediaServiceIDs.put("twitter", sID);
    }

    public SQLiteDatabase getDatabase(){
        return this.socialMediaDBHelper.getWritableDatabase();
    }

    public long getSocialMediaServiceID(String serviceName) {
        return socialMediaServiceIDs.get(serviceName);
    }
}
