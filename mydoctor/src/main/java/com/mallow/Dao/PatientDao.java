package com.mallow.Dao;

import com.mallow.Model.Patient;

/**
 * Created by brahim on 8/12/17.
 */

public interface PatientDao {

    public Patient addPatient(Patient e);
    public void deletePatient(String username);

}
