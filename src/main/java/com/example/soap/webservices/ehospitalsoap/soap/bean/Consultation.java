package com.example.soap.webservices.ehospitalsoap.soap.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Consultation {
    private String disease;
    private Physician physician;

    public Consultation(String disease, Physician physician) {
        this.disease = disease;
        this.physician = physician;
    }
}