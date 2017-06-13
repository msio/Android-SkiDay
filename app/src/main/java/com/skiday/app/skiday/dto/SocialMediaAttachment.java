package com.skiday.app.skiday.dto;

import java.io.Serializable;

public class SocialMediaAttachment implements Serializable{
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
