package com.example.soap.webservices.ehospitalsoap.soap.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.ehospitalsoap.soap.bean.Physician;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Consultation;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Patient;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

@Component
public class PatientService {

	private static Map<String, Patient> users = new LinkedHashMap<>();

	static {
		Patient user1 = new Patient("Jane Doe", "jadoe", Gender.Male, 24, UserRoles.Patient, "1234");
		Patient user2 = new Patient("John Doe", "jdoe", Gender.Male, 24, UserRoles.Patient, "1234");

		users.put(user1.getUsername(), user1);
		users.put(user2.getUsername(), user2);

	}

	public Patient findByUsername(String username) {
		return users.get(username);
	}

	public Status addUser(Patient user) {
		if (users.get(user.getUsername()) != null) {
			throw new RuntimeException("user already Exists");
		}
		users.put(user.getUsername(), user);
		return Status.SUCCESS;
	}

	public boolean authenticatePatient(String username, String password) {
		Patient user = findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public Patient[] getAllUsers() {
		List<Patient> userList = new ArrayList<>(users.values());
		return userList.toArray(new Patient[userList.size()]);
	}

	public static Patient selectPhysician(String username, Physician selectedPhysician) throws Exception {
		Patient patient = users.get(username);
		patient.setSelectedPhysician(selectedPhysician);
		users.put(username, patient);

		return patient;
	}

	public static Patient getConsultation(String username, Consultation consultation)
			throws Exception {
		Patient patient = users.get(username);
		patient.setConsultation(consultation);
		users.put(username, patient);

		return patient;
	}

}
