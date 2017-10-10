package com.mallow.Dao;

import com.mallow.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/12/17.
 */
@Repository
public class PatientDaoImpl implements PatientDao {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient e) {
       return  patientRepository.save(e);

    }

    @Override
    public void deletePatient(String username) {

        patientRepository.delete(username);

    }
}
