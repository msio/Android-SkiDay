package com.skiday.app.skiday.dao;

import android.provider.BaseColumns;

public class SocialMediaPostContract {
    private SocialMediaPostContract(){};

    public static class SocialMediaPostEntry implements BaseColumns{
        public static final String TABLE_NAME = "SocialMediaPosts";
        public static final String COLUMN_POST_ID = "pid";
        public static final String COLUMN_POST_TIMESTAMP = "timestamp";
        public static final String COLUMN_POST_GEOLOCATION = "geolocation";
        public static final String COLUMN_POST_TEXT = "text";
        public static String[] POST_COLUMNS = {COLUMN_POST_ID, COLUMN_POST_TIMESTAMP, COLUMN_POST_GEOLOCATION, COLUMN_POST_TEXT};
    }

    public static class PostAttachment implements BaseColumns{
        public static final String TABLE_NAME = "Attachments";
        public static final String COLUMN_ATTACHMENT_ID = "aid";
        public static final String COLUMN_ATTACHMENT_MIMETYPE = "mimetype";
        public static final String COLUMN_ATTACHMENT_REFERENCE = "ref";
        public static final String COLUMN_ATTACHMENT_POSTID = "p_id";
        public static String[] ATTACHMENT_COLUMNS = {COLUMN_ATTACHMENT_ID, COLUMN_ATTACHMENT_MIMETYPE, COLUMN_ATTACHMENT_REFERENCE, COLUMN_ATTACHMENT_POSTID};

    }

    public static class PostFeedback implements BaseColumns{
        public static final String TABLE_NAME = "Feedback";
        public static final String COLUMN_FEEDBACK_ID = "fid";
        public static final String COLUMN_FEEDBACK_AMOUNT = "positivefeedback";
        public static final String COLUMN_FEEDBACK_COMMENT_AMOUNT = "amountofcomments";
        public static final String COLUMN_FEEDBACK_POSTID = "pid";
        public static final String COLUMN_FEEDBACK_SOCIAL_MEDIA_ID = "sid";
    }

    public static class SocialMediaService implements BaseColumns{
        public static final String TABLE_NAME = "SocialMediaServices";
        public static final String COLUMN_SOCIALMEDIA_SID = "sid";
        public static final String COLUMN_SOCIALMEDIA_NAME = "name";
        public static final String COLUMN_SOCIALMEDIA_FEEDBACK_STYLE = "feedbackstyle";
    }
}
