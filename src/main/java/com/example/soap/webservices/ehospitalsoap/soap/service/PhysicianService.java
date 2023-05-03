package com.example.soap.webservices.ehospitalsoap.soap.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.ehospitalsoap.soap.bean.Physician;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

@Component
public class PhysicianService {

	private static Map<String, Physician> physicians = new LinkedHashMap<>();

	static {
		Physician user1 = new Physician("Jane Doe", "jadoe@email.com", Gender.Male, 24, UserRoles.Physician, "1234");
		physicians.put(user1.getEmail(), user1);
	}

	public Physician findByEmail(String email) {
		return physicians.get(email);
	}

	public Status addPhysican(Physician user) {
		if (physicians.get(user.getEmail()) != null) {
			throw new RuntimeException("user already Exists");
		}
		physicians.put(user.getEmail(), user);
		return Status.SUCCESS;
	}

	public Physician[] getAllphysicians() {
		List<Physician> userList = new ArrayList<>(physicians.values());
		return userList.toArray(new Physician[userList.size()]);
	}
}
