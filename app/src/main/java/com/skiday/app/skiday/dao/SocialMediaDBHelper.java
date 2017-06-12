package com.skiday.app.skiday.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.skiday.app.skiday.dao.SocialMediaPostContract.SocialMediaPostEntry;
import com.skiday.app.skiday.dao.SocialMediaPostContract.PostAttachment;
import com.skiday.app.skiday.dao.SocialMediaPostContract.PostFeedback;
import com.skiday.app.skiday.dao.SocialMediaPostContract.SocialMediaService;

public class SocialMediaDBHelper extends SQLiteOpenHelper{
    private static final String LOG_TAG = "SOCIALMEDIA-DB-HELPER";

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "SocialMediaPosts.db";

    public static final String CREATE_POST_DATABASE_QUERY = "CREATE TABLE " + SocialMediaPostEntry.TABLE_NAME + " ( "
            + SocialMediaPostEntry.COLUMN_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SocialMediaPostEntry.COLUMN_POST_TEXT + " TEXT, "
            + SocialMediaPostEntry.COLUMN_POST_TIMESTAMP + " DATETIME NOT NULL, " + SocialMediaPostEntry.COLUMN_POST_GEOLOCATION + " VARCHAR(3))";

    public static final String CREATE_SOCIAL_MEDIA_SERVICE_QUERY = "CREATE TABLE " + SocialMediaService.TABLE_NAME + " ( "
            + SocialMediaService.COLUMN_SOCIALMEDIA_SID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SocialMediaService.COLUMN_SOCIALMEDIA_NAME + " TEXT, "
            + SocialMediaService.COLUMN_SOCIALMEDIA_FEEDBACK_STYLE + " STRING)";

    public static final String CREATE_ATTACHMENT_DATABASE_QUERY = "CREATE TABLE " + PostAttachment.TABLE_NAME + " ( "
            + PostAttachment.COLUMN_ATTACHMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + PostAttachment.COLUMN_ATTACHMENT_MIMETYPE + " TEXT, "
            + PostAttachment.COLUMN_ATTACHMENT_POSTID + " INTEGER NOT NULL, "
            + PostAttachment.COLUMN_ATTACHMENT_REFERENCE + " TEXT, "
            + "FOREIGN KEY (" + PostAttachment.COLUMN_ATTACHMENT_POSTID + ") REFERENCES " + SocialMediaPostEntry.TABLE_NAME + " (" + SocialMediaPostEntry.COLUMN_POST_ID + "))";

    public static final String CREATE_FEEDBACK_DATABASE_QUERY = "CREATE TABLE " + PostFeedback.TABLE_NAME + " ( "
            + PostFeedback.COLUMN_FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + PostFeedback.COLUMN_FEEDBACK_AMOUNT + " INTEGER NOT NULL, "
            + PostFeedback.COLUMN_FEEDBACK_POSTID + " INTEGER NOT NULL, "
            + PostFeedback.COLUMN_FEEDBACK_SOCIAL_MEDIA_ID + " INTEGER NOT NULL, "
            + PostFeedback.COLUMN_FEEDBACK_COMMENT_AMOUNT + " INTEGER NOT NULL, "
            + "FOREIGN KEY ( " + PostFeedback.COLUMN_FEEDBACK_POSTID + " ) REFERENCES " + SocialMediaPostEntry.TABLE_NAME + " ( " + SocialMediaPostEntry.COLUMN_POST_ID + " ), "
            + "FOREIGN KEY ( " + PostFeedback.COLUMN_FEEDBACK_SOCIAL_MEDIA_ID
            + " ) REFERENCES " + SocialMediaService.TABLE_NAME + " ( " + SocialMediaService.COLUMN_SOCIALMEDIA_SID + " ))";


    public SocialMediaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Attempting to create Databases...");
        db.execSQL(CREATE_POST_DATABASE_QUERY);
        db.execSQL(CREATE_SOCIAL_MEDIA_SERVICE_QUERY);
        db.execSQL(CREATE_ATTACHMENT_DATABASE_QUERY);
        db.execSQL(CREATE_FEEDBACK_DATABASE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SocialMediaService.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PostFeedback.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PostAttachment.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SocialMediaPostEntry.TABLE_NAME);
        onCreate(db);
    }
}
