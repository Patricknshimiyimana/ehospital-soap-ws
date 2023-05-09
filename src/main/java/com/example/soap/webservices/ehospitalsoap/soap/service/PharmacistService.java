package com.example.soap.webservices.ehospitalsoap.soap.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.ehospitalsoap.soap.bean.Pharmacist;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

@Component
public class PharmacistService {

	private static Map<String, Pharmacist> pharmacists = new LinkedHashMap<>();

	static {
		Pharmacist user1 = new Pharmacist("Jane Doe", "jadoe@email.com", Gender.Male, 24, UserRoles.Pharmacist, "1234");
		pharmacists.put(user1.getPhone(), user1);
	}

	public Pharmacist findByPhone(String phone) {
		return pharmacists.get(phone);
	}

	public Status addPharmacist(Pharmacist user) {
		if (pharmacists.get(user.getPhone()) != null) {
			throw new RuntimeException("user already Exists");
		}
		pharmacists.put(user.getPhone(), user);
		return Status.SUCCESS;
	}

	public Pharmacist[] getAllPharmacists() {
		List<Pharmacist> userList = new ArrayList<>(pharmacists.values());
		return userList.toArray(new Pharmacist[userList.size()]);
	}

	public boolean authenticatePharmacist(String phone, String password) {
		Pharmacist user = findByPhone(phone);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}
