package com.mallow.brahim.mydocandroid.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by brahim on 8/6/17.
 */

public class Patient  extends Person implements Serializable {


    private Set<Appointment> appointments;

    private Set<Doctor> doctors;

    public Patient(String username, String f, String l, Date i, String s, String s1, String myadress, String pass) {
        super(username, f, l, i, s, s1, myadress, pass);
    }
    public Patient(){
        super();
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
