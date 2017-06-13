package com.skiday.app.skiday.dao;

import android.content.ContentValues;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.skiday.app.skiday.dto.SocialMediaAttachment;
import com.skiday.app.skiday.dto.SocialMediaFeedback;
import com.skiday.app.skiday.dto.SocialMediaPostDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialMediaDAO implements ISocialMediaDAO,Serializable{
    private static final String RETRIEVE_ALL_POSTS_STATEMENT = "SELECT * FROM " + SocialMediaPostContract.SocialMediaPostEntry.TABLE_NAME;
    private static final String RETRIEVE_ATTACHMENT = "SELECT * FROM " + SocialMediaPostContract.PostAttachment.TABLE_NAME;
    private static final String RETRIEVE_FEEDBACK = "SELECT * FROM " + SocialMediaPostContract.PostFeedback.TABLE_NAME + " FEED INNER JOIN " + SocialMediaPostContract.SocialMediaService.TABLE_NAME + " MEDIA ON " +
            " FEED."+SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_SOCIAL_MEDIA_ID + " = MEDIA." + SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_SID +
            " WHERE FEED." + SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_POSTID + " = ?";

    private SocialMediaDatabase databaseHelper;

    public SocialMediaDAO(SocialMediaDatabase db){
        this.databaseHelper = db;
    }

    @Override
    public void addSocialMediaPost(SocialMediaPostDTO socialMediaPostDTO) {
        ContentValues postValues = new ContentValues();
        postValues.put(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_TIMESTAMP, socialMediaPostDTO.getTimestamp());
        postValues.put(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_GEOLOCATION, socialMediaPostDTO.getGeoLocation());
        postValues.put(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_TEXT, socialMediaPostDTO.getText());

        long pID = this.databaseHelper.getDatabase().insert(SocialMediaPostContract.SocialMediaPostEntry.TABLE_NAME, null, postValues);
        long aID;

        if(socialMediaPostDTO.getSocialMediaAttachment() != null){
            ContentValues attachmentValues = new ContentValues();
            attachmentValues.put(SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_MIMETYPE, socialMediaPostDTO.getSocialMediaAttachment().getMimeType());
            attachmentValues.put(SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_REFERENCE, socialMediaPostDTO.getSocialMediaAttachment().getReference());
            attachmentValues.put(SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_POSTID, pID);
            aID = this.databaseHelper.getDatabase().insert(SocialMediaPostContract.PostAttachment.TABLE_NAME, null, attachmentValues);
        }

        for(SocialMediaFeedback feedback : socialMediaPostDTO.getFeedback()){
            ContentValues feedbackValues = new ContentValues();
            feedbackValues.put(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_POSTID, pID);
            feedbackValues.put(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_SOCIAL_MEDIA_ID, databaseHelper.getSocialMediaServiceID(feedback.getServiceName().toLowerCase()));

            feedbackValues.put(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_AMOUNT, feedback.getFeedbackAmount());
            feedbackValues.put(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_COMMENT_AMOUNT, feedback.getCommentAmount());
            long fID = this.databaseHelper.getDatabase().insert(SocialMediaPostContract.PostFeedback.TABLE_NAME, null, feedbackValues);
        }
    }

    @Override
    public List<SocialMediaPostDTO> getAllSocialMediaPosts() {
        List<SocialMediaPostDTO> socialMediaPostDTOs = new ArrayList<>();
        Cursor cursor = databaseHelper.getDatabase().rawQuery(RETRIEVE_ALL_POSTS_STATEMENT, null);

        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                SocialMediaPostDTO post = new SocialMediaPostDTO();

                String text = cursor.getString(cursor.getColumnIndex(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_TEXT));
                String geoLocation = cursor.getString(cursor.getColumnIndex(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_GEOLOCATION));
                String timestamp = cursor.getString(cursor.getColumnIndex(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_TIMESTAMP));

                post.setText(text);
                post.setTimestamp(timestamp);
                post.setGeoLocation(geoLocation);
                long pID = cursor.getLong(cursor.getColumnIndex(SocialMediaPostContract.SocialMediaPostEntry.COLUMN_POST_ID));

                String[] whereArgs = new String[1];
                whereArgs[0] = pID + "";
                Cursor attachmntCursor = databaseHelper.getDatabase().query(SocialMediaPostContract.PostAttachment.TABLE_NAME, null, SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_POSTID + " = ?", whereArgs, null, null, null);

                if(attachmntCursor.getCount() != 0){
                    attachmntCursor.moveToFirst();
                    SocialMediaAttachment attachment = new SocialMediaAttachment();
                    attachment.setMimeType(attachmntCursor.getString(attachmntCursor.getColumnIndex(SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_MIMETYPE)));
                    attachment.setReference(attachmntCursor.getString(attachmntCursor.getColumnIndex(SocialMediaPostContract.PostAttachment.COLUMN_ATTACHMENT_REFERENCE)));
                    post.setSocialMediaAttachment(attachment);
                }

                String[] feedbackWhereArgs = new String[1];
                feedbackWhereArgs[0] = pID + "";
                int totalComments = 0;
                Cursor socialMediaCursor = databaseHelper.getDatabase().rawQuery(RETRIEVE_FEEDBACK, feedbackWhereArgs);
                assert(socialMediaCursor != null);
                if(socialMediaCursor.getCount() != 0){
                    socialMediaCursor.moveToFirst();
                    while(!socialMediaCursor.isAfterLast()){
                        SocialMediaFeedback feedback = new SocialMediaFeedback();
                        feedback.setCommentAmount(socialMediaCursor.getInt(socialMediaCursor.getColumnIndex(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_COMMENT_AMOUNT)));
                        feedback.setFeedbackAmount(socialMediaCursor.getInt(socialMediaCursor.getColumnIndex(SocialMediaPostContract.PostFeedback.COLUMN_FEEDBACK_AMOUNT)));
                        feedback.setFeedbackStyle(socialMediaCursor.getString(socialMediaCursor.getColumnIndex(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_FEEDBACK_STYLE)));
                        feedback.setServiceName(socialMediaCursor.getString(socialMediaCursor.getColumnIndex(SocialMediaPostContract.SocialMediaService.COLUMN_SOCIALMEDIA_NAME)));

                        post.addFeedback(feedback);
                        socialMediaCursor.moveToNext();
                    }
                }

                socialMediaPostDTOs.add(post);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return socialMediaPostDTOs;
    }
}
