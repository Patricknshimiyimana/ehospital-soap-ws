package com.example.soap.webservices.ehospitalsoap.soap.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prescription {
    private String disease;
    private Medecine medicine;
    // constructor, getters and setters

    public Prescription(String disease, Medecine medicine) {
        this.disease = disease;
        this.medicine = medicine;
    }

}
