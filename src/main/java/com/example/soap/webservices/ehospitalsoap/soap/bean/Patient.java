package com.example.soap.webservices.ehospitalsoap.soap.bean;

import java.util.UUID;

import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Patient {
    public String id;
    @NonNull
    public String fullNames;
    public String username;
    private Gender gender;
    private Integer age;
    protected UserRoles role;
    private Physician selectedPhysician;
    private Pharmacist selectedPharmacist;
    public Consultation consultation;
    public Prescription prescription;
    // @NonNull
    // @Expose(serialize = false)
    private String password;

    public Patient(String fullNames, String username, Gender gender, Integer age, UserRoles role, String password) {
        this.id = UUID.randomUUID().toString();
        this.age = age;
        this.fullNames = fullNames;
        this.username = username;
        this.gender = gender;
        this.role = role;
        this.password = password;

    }

    // public User(String fullNames2, String username2, com.example.ehospital.Gender
    // mapGender, int age2,
    // com.example.ehospital.UserRoles mapUserRoles, String password2) {
    // }

    // public User() {
    // this.id = UUID.randomUUID().toString();
    // }

    // public User(String fullNames, Gender gender, Integer age, UserRoles userRole,
    // String password) {
    // this.id = UUID.randomUUID().toString();
    // this.age = age;
    // this.fullNames = fullNames;
    // this.gender = gender;
    // this.role = userRole;
    // this.password = password;

    // }

    // @Override
    // public ResponseEntity<User> register() throws Exception {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'register'");
    // }

    // @Override
    // public ResponseEntity<String> login(String email, String Password) throws
    // AuthenticationException {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'login'");
    // }

}