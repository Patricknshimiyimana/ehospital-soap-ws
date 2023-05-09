package com.example.soap.webservices.ehospitalsoap.soap.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medecine {
    private String medName;
    private double medPrice;
    private String expirationDate;

    public Medecine(String medName, double medPrice, String expirationDate) {
        this.medName = medName;
        this.medPrice = medPrice;
        this.expirationDate = expirationDate;
    }

    public Medecine() {
    }
}
