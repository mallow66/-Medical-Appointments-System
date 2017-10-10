package com.mallow.Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by brahim on 8/6/17.
 */
@Entity
@Table
@PrimaryKeyJoinColumn
public class Secretary extends Person implements Serializable {

    public Secretary(String username, String firstName, String lastName, Date dateOfBirth, String email, String phone, String address, String password) {
       super(username, firstName, lastName, dateOfBirth, email, phone, address, password);
    }

    public Secretary(){

    }

}
