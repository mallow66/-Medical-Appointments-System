package com.mallow.Dao;

import com.mallow.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/20/17.
 */
@Repository
public interface  DoctorRepository extends JpaRepository<Doctor, String>{
}
