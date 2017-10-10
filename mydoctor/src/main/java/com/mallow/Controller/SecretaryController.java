package com.mallow.Controller;

import com.mallow.Dao.DoctorRepository;
import com.mallow.Dao.PatientRepository;
import com.mallow.Dao.SecretaryRepository;
import com.mallow.Model.Doctor;
import com.mallow.Model.Patient;
import com.mallow.Model.Secretary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by brahim on 8/23/17.
 */

@Controller
public class SecretaryController {

    @Autowired
    SecretaryRepository secretaryRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;


    @Secured(value = {"ROLE_SECRETARY"})
    @RequestMapping(value = "/addSecretary", method = RequestMethod.GET)
    public String addSecretary(Secretary secretary, Model model){
        model.addAttribute("secratary", new Secretary());
        return "addSecretary";
    }


    @Secured(value = {"ROLE_SECRETARY"})
    @RequestMapping(value = "/addSecretary", method = RequestMethod.POST)
    public String addSecretaryPost(@ModelAttribute  @Valid Secretary secretary, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "addSecretary";
        }
        System.out.println("AddSecretary Post Request received !");
        Secretary sc =  secretaryRepository.findOne(secretary.getUsername());
        if(sc != null){
            System.out.println("Secretary already exists !");
            model.addAttribute("validUsername", "The secretary already exists !");
            return "addSecretary";
        }

        secretaryRepository.save(secretary);


        return "addSecretary";

    }

    @GetMapping("/changePassword")
    public String changePasswordGet(){

        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changeSecretaryPassword(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        Secretary secretary = secretaryRepository.findOne(username);

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
    System.out.print(oldPassword+" --------"+ newPassword);
        if(oldPassword.equals("") || newPassword.equals("")){
            model.addAttribute("zero", "Please check your inputs");
            System.out.print("im here !!");
            return "changePassword";
        }

        if(!secretary.getPassword().equals(oldPassword)){
            model.addAttribute("old", "This is not the old password");
            return "changePassword";
        }

        secretary.setPassword(newPassword);
        secretaryRepository.save(secretary);

        System.out.print("Password changed succesfully");

        return "changePassword";
    }

    @GetMapping("/addDoctor")
    @Secured(value = {"ROLE_SECRETARY"})
    public String addDoctor(Model model){
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }
    @PostMapping("/addDoctor")
    public String addDoctorPost(@ModelAttribute @Valid  Doctor doctor, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "addDoctor";
        }
        System.out.println("AddSecretary Post Request received !");
        Doctor doc =  doctorRepository.findOne(doctor.getUsername());
        if(doc != null){
            System.out.println("Doctor already exists !");
            model.addAttribute("validUsername", "The Doctor already exists !");
            return "addDoctor";
        }

       doctorRepository.save(doctor);
        model.addAttribute("success", "The doctor was added succefully !");
        return "addDoctor";
    }

    @GetMapping("/doctors")
    @Secured(value = {"ROLE_SECRETARY"})
    public String getDoctors(Model model){

        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);


        return "doctors";
    }

    @GetMapping("/doctorDetails")
    @Secured(value = {"ROLE_SECRETARY"})
    public String doctorDetails(@RequestParam("username") String username, Model model){



        model.addAttribute("doctor",doctorRepository.findOne(username));
        model.addAttribute("patients", patientRepository.findAll() );

        return "doctorDetails";
    }




    @PostMapping("/addPatientToDoctor")
    @Secured(value = {"ROLE_SECRETARY"})
    public String addPatientToDoctorPost(HttpServletRequest request, Model model){
        System.out.println("select ");

        String patient = request.getParameter("patient");
        if(patient.equals("")){
            System.out.println("select NULL");
            model.addAttribute("error", "Please select a patient from the list ");
            return "addPatientToDoctor";
        }
        Doctor doctor = doctorRepository.findOne(request.getParameter("username"));
        Patient p = patientRepository.findOne(patient);
        p.getDoctors().add(doctor);
        patientRepository.save(p);
        System.out.println("select NOT NULL");

        return "addPatientToDoctor";



    }


}
