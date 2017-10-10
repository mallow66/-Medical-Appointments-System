package com.mallow;

import com.mallow.Dao.AppoitmentRepository;
import com.mallow.Dao.PatientRepository;
import com.mallow.Dao.RoleRepository;
import com.mallow.Dao.SecretaryRepository;
import com.mallow.Model.*;
import javafx.scene.effect.SepiaTone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MydoctorApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(MydoctorApplication.class, args);
		SecretaryRepository secretaryRepository = ctx.getBean(SecretaryRepository.class);
		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		PatientRepository patientRepository = ctx.getBean(PatientRepository.class);
		AppoitmentRepository appoitmentRepository = ctx.getBean(AppoitmentRepository.class);

		Secretary admin = new Secretary("admin", "admin", "admin", new Date(), "admin@adoc.com", "000000000", "@admin", "admin");
		Role doctorRole = new Role("DOCTOR", "doctor's role");
		Role secretaryRole = new Role("SECRETARY", "secretary's role");
		Role patientRole = new Role("PATIENT", "patient's role");

		roleRepository.save(doctorRole);
		roleRepository.save(secretaryRole);
		roleRepository.save(patientRole);

		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(roleRepository.findOne("SECRETARY"));

		admin.setRoles(adminRoles);

		secretaryRepository.save(admin);



		//for tests ::

		Patient patient1 = new Patient("patient1", "patient1", "patient1", new Date(), "patient1", "patient1", "patient1", "patient1");
		Patient patient2 =  new Patient("patient2", "patient2", "patient2", new Date(), "patient2", "patient2", "patient2", "patient2");

		Doctor doctor1 = new Doctor("doctor1", "doctor1", "doctor1", new Date(), "doctor1", "doctor1", "doctor1", "doctor1");
		Doctor doctor2 = new Doctor("doctor2", "doctor2", "doctor2", new Date(), "doctor2", "doctor2", "doctor2", "doctor2");






		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = dateFormat.parse("2017-09-29 22:00");
		Date date2 = dateFormat.parse("2017-09-29 23:00");
		Appointment appointment1 = new Appointment("1", "Appointement1", patient1, doctor1, date, "Note appointement");
		Appointment appointment2 = new Appointment("2", "Appointement1", patient1, doctor1, date2, "Note appointement");

		Set<Appointment> appointments = new HashSet<>();
		appointments.add(appointment1);
		appointments.add(appointment2);

		Set<Doctor> doctors = new HashSet<>();
		doctor1.setAppointments(appointments);
		doctors.add(doctor1);
		doctors.add(doctor2);





		appoitmentRepository.save(appointments);

		patient1.setAppointments(appointments);
		patient1.setDoctors(doctors);
		patientRepository.save(patient1);









	}
}
