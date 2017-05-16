package com.skiday.app.skiday.model;

/**
 * Created by jan on 15.05.17.
 */

public class MeantimeResult {

    private int id;
    private int lapNumber;
    private int meantimeNumber;
    private int time;

    public MeantimeResult(int id, int lapNumber, int meantimeNumber, int time) {
        this.id = id;
        this.lapNumber = lapNumber;
        this.meantimeNumber = meantimeNumber;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
    }

    public int getMeantimeNumber() {
        return meantimeNumber;
    }

    public void setMeantimeNumber(int meantimeNumber) {
        this.meantimeNumber = meantimeNumber;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
