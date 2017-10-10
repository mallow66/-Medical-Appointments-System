package com.mallow.Dao;

import com.mallow.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/12/17.
 */
@Repository
public interface PatientRepository  extends JpaRepository<Patient, String>{

}
