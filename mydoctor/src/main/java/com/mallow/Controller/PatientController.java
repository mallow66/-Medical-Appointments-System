package com.mallow.Controller;

import com.mallow.Dao.DoctorRepository;
import com.mallow.Dao.PatientRepository;
import com.mallow.Model.Appointment;
import com.mallow.Model.Doctor;
import com.mallow.Model.Patient;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by brahim on 8/29/17.
 */
@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;


    @Autowired
    DoctorRepository doctorRepository;



    @RequestMapping(value = "/infoPatient", method = RequestMethod.POST)
    public Patient infoSessionPatient(@RequestBody Map<String, String> request){

        System.out.println("I am Here :  Info Patient");
        return patientRepository.findOne(request.get("patient"));

    }


    @RequestMapping(value = "/loginclient", method = RequestMethod.POST)
    public Map<String, Object> loginClient(@RequestBody Map<String, String> request){
        System.out.print("I am Here  !!!!!");
        Map<String, Object> params = new HashMap<>();

        String username = request.get("username");
        String password = request.get("password");

        System.out.println(username+" _______"+ password);

        Patient patient = null;
        patient = patientRepository.findOne(username);
        if(patient == null){
            params.put("success", false);
            params.put("details", "Patient Not Found !");
            return params;
        }
        if(!patient.getPassword().equals(password)){
            params.put("success", false);
            params.put("details", "invalid password");
            return params;
        }

        params.put("success", true);
        params.put("details", "successfully Login !");
        return params;



    }


    @RequestMapping(value = "/mydoctors", method = RequestMethod.POST)
    public Set<Doctor> getMyDoctors(@RequestBody Map<String, String> params){
        System.out.println("HELLOOOOOOO");
        Patient p = patientRepository.findOne(params.get("patient"));

        return p.getDoctors();

    }

    @RequestMapping(value = "/mydoctordetails", method = RequestMethod.POST)
    public Object getMyDoctorDetails(@RequestBody Map<String, String> params){
        System.out.println("MY DOCTOR DETAILS :::::::::::");

        Doctor d = doctorRepository.findOne(params.get("doctor"));

        return d.getAppointments();
    }

    @RequestMapping("/testApp")
    public Set<Appointment> testAppoitments(){
       return doctorRepository.findOne("doctor1").getAppointments();
    }
}
