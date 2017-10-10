package com.mallow.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by brahim on 8/6/17.
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name = "id_doctor")
public class Doctor extends Person  implements Serializable {

    @OneToMany
    @JoinColumn(name = "id_doctor53")
    private Set<Appointment> appointments;

    @ManyToMany(cascade=CascadeType.ALL ,mappedBy="doctors")
    private Set<Patient> patients;

    public Doctor(String username, String firstName, String lastName, Date dateOfBirth, String email, String phone, String address, String password) {
        super(username, firstName, lastName, dateOfBirth, email, phone, address, password);
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Doctor(){

    }


}
