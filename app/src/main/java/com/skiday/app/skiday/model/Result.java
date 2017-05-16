package com.skiday.app.skiday.model;

/**
 * Created by jan on 03.05.17.
 */

public class Result {

    private int time;
    private int relativeToBest;
    private int relativeToMe;

    public Result() {
    }

    public Result(int time, int relativeToBest, int relativeToMe) {
        this.time = time;
        this.relativeToBest = relativeToBest;
        this.relativeToMe = relativeToMe;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRelativeToBest() {
        return relativeToBest;
    }

    public void setRelativeToBest(int relativeToBest) {
        this.relativeToBest = relativeToBest;
    }

    public int getRelativeToMe() {
        return relativeToMe;
    }

    public void setRelativeToMe(int relativeToMe) {
        this.relativeToMe = relativeToMe;
    }
}
