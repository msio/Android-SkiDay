package com.skiday.app.skiday.model;

/**
 * Created by jan on 03.05.17.
 */

public class Result {

    private String time;
    private String relativeToBest;
    private String relativeToMe;

    public Result() {
    }

    public Result(String time, String relativeToBest, String relativeToMe) {
        this.time = time;
        this.relativeToBest = relativeToBest;
        this.relativeToMe = relativeToMe;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRelativeToBest() {
        return relativeToBest;
    }

    public void setRelativeToBest(String relativeToBest) {
        this.relativeToBest = relativeToBest;
    }

    public String getRelativeToMe() {
        return relativeToMe;
    }

    public void setRelativeToMe(String relativeToMe) {
        this.relativeToMe = relativeToMe;
    }
}
