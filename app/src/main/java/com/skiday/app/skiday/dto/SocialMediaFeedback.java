package com.skiday.app.skiday.dto;

import java.io.Serializable;

public class SocialMediaFeedback implements Serializable{
    private String serviceName;
    private String feedbackStyle;
    private int feedbackAmount;
    private int commentAmount;

    public SocialMediaFeedback(String name, String style) {
        this.commentAmount = 0;
        this.feedbackAmount = 0;
        this.serviceName = name;
        this.feedbackStyle = style;
    }

    public SocialMediaFeedback() {
    }

    public int getFeedbackAmount() {
        return feedbackAmount;
    }

    public void setFeedbackAmount(int feedbackAmount) {
        this.feedbackAmount = feedbackAmount;
    }

    public int getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(int commentAmount) {
        this.commentAmount = commentAmount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFeedbackStyle() {
        return feedbackStyle;
    }

    public void setFeedbackStyle(String feedbackStyle) {
        this.feedbackStyle = feedbackStyle;
    }
}
