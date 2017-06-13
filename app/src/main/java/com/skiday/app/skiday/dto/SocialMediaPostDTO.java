package com.skiday.app.skiday.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaPostDTO implements Serializable{
    private String text;
    private String timestamp;
    private String geoLocation;
    private List<SocialMediaFeedback> feedback;
    private SocialMediaAttachment socialMediaAttachment;

    public SocialMediaPostDTO(String text, String timestamp, String geoLocation, List<SocialMediaFeedback> feedback, SocialMediaAttachment socialMediaAttachment) {
        this.text = text;
        this.timestamp = timestamp;
        this.geoLocation = geoLocation;
        this.feedback = feedback;
        this.socialMediaAttachment = socialMediaAttachment;
    }

    public SocialMediaPostDTO() {
        this.feedback = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public List<SocialMediaFeedback> getFeedback() {
        return feedback;
    }

    public void addFeedback(SocialMediaFeedback feedback){
        this.feedback.add(feedback);
    }

    public void setFeedback(List<SocialMediaFeedback> feedback) {
        this.feedback = feedback;
    }

    public SocialMediaAttachment getSocialMediaAttachment() {
        return socialMediaAttachment;
    }

    public void setSocialMediaAttachment(SocialMediaAttachment socialMediaAttachment) {
        this.socialMediaAttachment = socialMediaAttachment;
    }
}
