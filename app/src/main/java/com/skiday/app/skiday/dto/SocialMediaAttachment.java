package com.skiday.app.skiday.dto;

public class SocialMediaAttachment {
    private String mimeType;
    private String reference;


    public SocialMediaAttachment() {
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
