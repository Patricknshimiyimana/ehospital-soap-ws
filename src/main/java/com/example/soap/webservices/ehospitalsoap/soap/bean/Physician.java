package com.example.soap.webservices.ehospitalsoap.soap.bean;

import java.util.UUID;

import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Physician {
    public String id;
    @NonNull
    public String fullNames;
    public String email;
    private Gender gender;
    private Integer age;
    protected UserRoles role;
    @NonNull
    // @Expose(serialize = false)
    private String password;

    public Physician(String fullNames, String email, Gender gender, Integer age, UserRoles role, String password) {
        this.id = UUID.randomUUID().toString();
        this.age = age;
        this.fullNames = fullNames;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.password = password;

    }

}