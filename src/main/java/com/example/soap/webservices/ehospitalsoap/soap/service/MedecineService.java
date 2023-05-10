package com.example.soap.webservices.ehospitalsoap.soap.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.soap.webservices.ehospitalsoap.soap.bean.Medecine;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;

@Component
public class MedecineService {
	private static Map<String, Medecine> medicines = new LinkedHashMap<>();

	public Status addMedecine(Medecine med) {
		if (medicines.get(med.getMedName()) != null) {
			throw new RuntimeException("Medicine with the same name already exists.");
		}

		medicines.put(med.getMedName(), med);
		return Status.SUCCESS;
	}

	public Medecine[] getMedicines() {
		Collection<Medecine> medValues = medicines.values();
		return medValues.toArray(new Medecine[medicines.size()]);
	}

	public Medecine findMedicine(String medName) {
		return medicines.get(medName);
	}
}
