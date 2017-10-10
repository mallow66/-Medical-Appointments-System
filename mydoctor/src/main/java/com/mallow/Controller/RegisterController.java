package com.mallow.Controller;

import com.mallow.Dao.*;
import com.mallow.Model.*;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by brahim on 8/11/17.
 */
@RestController
public class RegisterController {
    @Autowired
    PatientDao patientDao;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    SecretaryRepository secretaryRepository;


//
@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
public Object insertPatient(@RequestBody  Patient patient, BindingResult bindingResult){


    return patientDao.addPatient(patient);
}

  @RequestMapping(value = "/registerp", method = RequestMethod.POST)
    public Object savePatient(@RequestBody  @Valid  Patient patient, BindingResult bindingResult){
      System.out.println("I AM HEEEEEEEEEEEEEEEEERRE !!!!!!!!!!!");

      if(bindingResult.hasErrors()){
          Map<String, Object> errors = new HashMap<>();
          errors.put("errors", true);
          for(FieldError fe : bindingResult.getFieldErrors()){
              errors.put(fe.getField(), fe.getDefaultMessage());

          }

          for(Map.Entry<String, Object> entry : errors.entrySet()){
              System.out.println(entry.getKey()+ " --"+ entry.getValue());
          }
          return errors;
      }

      return patientDao.addPatient(patient);








    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public Person getPerson(){

        return  new Person("brahimmix", "brahim", "serghini", null, "okqsnf", "kqsn", "skn", "kqsqf");

    }





    private Map<String, Object> getLoggedUser(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        String username = securityContext.getAuthentication().getName();

        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        List<String> roles = new ArrayList<>();

        for(GrantedAuthority ga :  securityContext.getAuthentication().getAuthorities()){
            roles.add(ga.getAuthority());
        }
        user.put("roles", roles);

        return user;


    }

    @GetMapping("/getLoggedUser")
    public Person getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        String username = securityContext.getAuthentication().getName();

        return personRepository.findOne(username);

    }



}
