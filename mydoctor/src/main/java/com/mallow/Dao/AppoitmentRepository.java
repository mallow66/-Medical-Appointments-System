package com.mallow.Dao;

import com.mallow.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by brahim on 8/20/17.
 */
@Repository
public interface AppoitmentRepository extends JpaRepository<Appointment, String>{

}
