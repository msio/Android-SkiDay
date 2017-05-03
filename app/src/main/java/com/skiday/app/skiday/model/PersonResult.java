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
}
