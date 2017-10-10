package com.mallow.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by brahim on 8/6/17.
 */
@Entity
@Table
public class Appointment implements Serializable {

    @Id
    private String idAppointmemt;
    @Column
    private String caption;
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    //@JoinColumn(name = "id_patient")
    //private Patient patient;
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_doctor")
    //private Doctor doctor;
    @Column
    private Date date;
    @Column
    private String doctorNotes;


    public Appointment(){

    }
    public Appointment(String idAppointmemt, String caption, Patient patient, Doctor doctor, Date date, String doctorNotes) {
        this.idAppointmemt = idAppointmemt;
        this.caption = caption;
      //  this.patient = patient;
      //  this.doctor = doctor;
        this.date = date;
        this.doctorNotes = doctorNotes;
    }

    public String getIdAppointmemt() {
        return idAppointmemt;
    }

    public String getCaption() {
        return caption;
    }





    public Date getDate() {
        return date;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }
}
