package com.mallow.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by brahim on 8/12/17.
 */
@Entity
public class Role implements Serializable {

    @Id
    private String name;
    @Column
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

    @Override
    public boolean equals(Object obj) {
        if( obj == null) return false;
        if( this == obj) return true;
        if(obj.getClass() == Role.class){
            return ((Role)obj).getName().equals(this.getName());
        }
        return false;
    }
}
