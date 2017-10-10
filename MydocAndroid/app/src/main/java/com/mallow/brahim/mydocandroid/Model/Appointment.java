package com.mallow.brahim.mydocandroid.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by brahim on 8/6/17.
 */

public class Appointment implements Serializable {


    private String idAppointmemt;

    private String caption;

    private Date date;

    private String doctorNotes;


    public String getIdAppointmemt() {
        return idAppointmemt;
    }

    public void setIdAppointmemt(String idAppointmemt) {
        this.idAppointmemt = idAppointmemt;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
}
