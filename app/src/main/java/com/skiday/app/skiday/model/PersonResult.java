package com.skiday.app.skiday.model;

/**
 * Created by jan on 03.05.17.
 */

public class PersonResult {

    private String name;
    private int pictureId;
    private int rank;

    private Lap lap1;
    private Lap lap2;
    private Result total;

    public PersonResult(String name) {
        this.name = name;
    }

    public Lap getLap1() {
        return lap1;
    }

    public void setLap1(Lap lap1) {
        this.lap1 = lap1;
    }

    public Lap getLap2() {
        return lap2;
    }

    public void setLap2(Lap lap2) {
        this.lap2 = lap2;
    }

    public Result getTotal() {
        return total;
    }

    public void setTotal(Result total) {
        this.total = total;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String timeToRelativeString(int time){
        return (time >=0) ? "+"+timeToString(time) : timeToString(time);
    }

    public static String timeToString(int time){
        String timeString = "";
        int min, sec, milli;
        boolean negative = false;

        if (time < 0){
            negative = true;
            time = (-1)*time;
        }

        min = (Math.abs(time/6000));
        time = (Math.abs(time%6000));
        sec = time/100;
        milli = time%100;

        if (min != 0) {

            //String tmp = String.format("%1$02d", foo).toString();
            timeString = String.format("%1$02d:%2$02d,%3$02d", min, sec, milli).toString();
        }else {
            timeString = String.format("%1$02d,%2$02d", sec, milli).toString();
        }

        if (negative)
            timeString = "-"+timeString;

        return timeString;
    }

    public static String timeToString2(int time){
        String timeString = "";
        int min, sec, milli;

        min = (Math.abs(time/6000));
        time = (Math.abs(time%6000));
        sec = time/100;
        milli = time%100;
        timeString = String.format("%1$02d:%2$02d,%3$02d", min, sec, milli).toString();

        return timeString;
    }

}
