package com.skiday.app.skiday.model;

/**
 * Created by jan on 15.05.17.
 */

public class MeantimeResultLine {

    private int time;
    private int absoluteTimeBest;
    private int absoluteTimeMe;
    private int relBest;
    private int relMe;
    private Person best;
    private Person person;

    public MeantimeResultLine(int time, int relBest, int relMe, int absoluteTimeBest, int absoluteTimeMe, Person best, Person person) {
        this.time = time;
        this.relBest = relBest;
        this.relMe = relMe;
        this.best = best;
        this.person = person;
        this.absoluteTimeBest = absoluteTimeBest;
        this.absoluteTimeMe = absoluteTimeMe;
    }

    public int getAbsoluteTimeBest() {
        return absoluteTimeBest;
    }

    public void setAbsoluteTimeBest(int absoluteTimeBest) {
        this.absoluteTimeBest = absoluteTimeBest;
    }

    public int getAbsoluteTimeMe() {
        return absoluteTimeMe;
    }

    public void setAbsoluteTimeMe(int absoluteTimeMe) {
        this.absoluteTimeMe = absoluteTimeMe;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRelBest() {
        return relBest;
    }

    public void setRelBest(int relBest) {
        this.relBest = relBest;
    }

    public int getRelMe() {
        return relMe;
    }

    public void setRelMe(int relMe) {
        this.relMe = relMe;
    }

    public Person getBest() {
        return best;
    }

    public void setBest(Person best) {
        this.best = best;
    }

    @Override
    public String toString() {
        return "MeantimeResultLine{" +
                "time=" + time +
                ", relBest=" + relBest +
                ", relMe=" + relMe +
                ", best=" + best +
                ", person=" + person +
                '}';
    }
}
