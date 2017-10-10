package com.mallow.Controller;

import com.mallow.Dao.AppoitmentRepository;
import com.mallow.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brahim on 8/20/17.
 */
@RestController
public class AppoitmentController {


    @Autowired
    AppoitmentRepository appoitmentRepository;

    @RequestMapping(value = "/addAppoitment", method = RequestMethod.GET)
    public Appointment addAppoitment(@RequestBody Appointment appointment){

        return appoitmentRepository.save(appointment);
    }
}
