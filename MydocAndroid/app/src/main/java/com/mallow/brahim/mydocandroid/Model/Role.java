package com.mallow.brahim.mydocandroid.Model;


import java.io.Serializable;

/**
 * Created by brahim on 8/12/17.
 */

public class Role implements Serializable {


    private String name;

    private String description;


    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(String name){
        this.name = name;

    }

    public Role(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
