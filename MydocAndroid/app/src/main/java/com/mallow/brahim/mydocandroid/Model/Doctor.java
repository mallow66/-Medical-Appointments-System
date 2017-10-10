package com.mallow.brahim.mydocandroid.Model;


import java.io.Serializable;
import java.util.Set;

/**
 * Created by brahim on 8/6/17.
 */

public class Doctor extends Person  implements Serializable {

    private Set<Appointment> appointments;

    private Set<Patient> patients;

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
