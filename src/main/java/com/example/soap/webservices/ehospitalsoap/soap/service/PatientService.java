package com.example.soap.webservices.ehospitalsoap.soap.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.ehospitalsoap.soap.bean.Physician;
import com.example.soap.webservices.ehospitalsoap.soap.bean.User;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

@Component
public class PatientService {

	private static Map<String, User> users = new LinkedHashMap<>();

	static {
		User user1 = new User("Jane Doe", "jadoe", Gender.Male, 24, UserRoles.Patient, "1234");
		User user2 = new User("John Doe", "jdoe", Gender.Male, 24, UserRoles.Patient, "1234");

		users.put(user1.getUsername(), user1);
		users.put(user2.getUsername(), user2);

	}

	public User findByUsername(String username) {
		return users.get(username);
	}

	public Status addUser(User user) {
		if (users.get(user.getUsername()) != null) {
			throw new RuntimeException("user already Exists");
		}
		users.put(user.getUsername(), user);
		return Status.SUCCESS;
	}

	public boolean authenticatePatient(String username, String password) {
		User user = findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public User[] getAllUsers() {
		List<User> userList = new ArrayList<>(users.values());
		return userList.toArray(new User[userList.size()]);
	}

	public static User selectPhysician(String username, Physician selectedPhysician) throws Exception {
		User patient = users.get(username);
		patient.setSelectedPhysician(selectedPhysician);
		users.put(username, patient);

		return patient;
	}

}
