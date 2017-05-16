package com.skiday.app.skiday.model;

/**
 * Created by jan on 15.05.17.
 */

public class Person {

    private String name;
    private int pictureId;
    private Country country;
    private int id;


    public Person() {
    }

    public Person(String name, int pictureId, Country country, int id) {
        this.name = name;
        this.pictureId = pictureId;
        this.country = country;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
