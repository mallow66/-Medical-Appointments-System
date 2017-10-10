package com.mallow.Model;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by brahim on 8/6/17.
 */

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    private String username;

    @Column
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;
    @Column
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dateOfBirth;
    @Column
    private String email;
    @Column
    @NotNull
    private String phone;
    @Column
    @NotNull
    private String address;
    @Column
    @NotNull
    private String password;

    //this attribute is used for the spring security Roles
    @ManyToMany
    @JoinTable(name= "persons_roles")
    private Set<Role> roles;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Person(){

    }

    public Person(String username, String firstName, String lastName, Date dateOfBirth, String email, String phone, String address, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
