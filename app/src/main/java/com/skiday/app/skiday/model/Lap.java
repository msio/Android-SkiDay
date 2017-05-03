package com.skiday.app.skiday.model;

/**
 * Created by jan on 03.05.17.
 */

public class Lap {

    private Result meantime1;
    private Result meantime2;
    private Result meantime3;
    private Result total;

    public Lap() {
    }

    public Result getMeantime1() {
        return meantime1;
    }

    public void setMeantime1(Result meantime1) {
        this.meantime1 = meantime1;
    }

    public Result getMeantime2() {
        return meantime2;
    }

    public void setMeantime2(Result meantime2) {
        this.meantime2 = meantime2;
    }

    public Result getMeantime3() {
        return meantime3;
    }

    public void setMeantime3(Result meantime3) {
        this.meantime3 = meantime3;
    }

    public Result getTotal() {
        return total;
    }

    public void setTotal(Result total) {
        this.total = total;
    }
}
