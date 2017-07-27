package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/7/20.
 */

public class PetMessageInfo implements Serializable {
    private String name;
    private String age;
    private String breed;

    private String label;
    private String id;
    private String petheadurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetheadurl() {
        return petheadurl;
    }

    public void setPetheadurl(String petheadurl) {
        this.petheadurl = petheadurl;
    }
}
