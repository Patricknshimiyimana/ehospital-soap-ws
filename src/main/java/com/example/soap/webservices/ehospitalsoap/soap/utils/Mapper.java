// package com.example.soap.webservices.ehospitalsoap.soap.utils;

// import com.example.ehospital.Physician;
// import com.example.ehospital.UserGenderType;
// import com.example.ehospital.UserRoleTypes;
// import com.example.soap.webservices.ehospitalsoap.soap.bean.Pharmacist;
// import com.example.soap.webservices.ehospitalsoap.soap.bean.User;
// import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
// import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;

// public class Mapper {
// public UserRoles mapUserRoles(UserRoleTypes role) {
// if (role == UserRoleTypes.PATIENT) {
// return UserRoles.Patient;
// }
// if (role == UserRoleTypes.PHYSICIAN) {
// return UserRoles.Physician;
// }
// if (role == UserRoleTypes.PHARMACIST) {
// return UserRoles.Pharmacist;
// }
// return null;
// }

// public Gender mapGender(UserGenderType gender) {
// if (gender == UserGenderType.MALE) {
// return Gender.Male;
// }
// return Gender.Female;

// }

// public com.example.ehospital.PatientDetails mapUser(User user) {
// com.example.ehospital.PatientDetails PatientDetails = new
// com.example.ehospital.PatientDetails();

// PatientDetails.setId(user.getId());
// PatientDetails.setUsername(user.getUsername());
// PatientDetails.setAge(user.getAge());
// PatientDetails.setFullNames(user.getFullNames());
// PatientDetails.setGender(user.getGender() == Gender.Female ?
// UserGenderType.FEMALE : UserGenderType.MALE);
// PatientDetails.setRole(mapUserRoleTypes(user.getRole()));
// // PatientDetails.setSe

// return PatientDetails;
// }

// public static com.example.ehospital.PharmacistDetails
// mapPharmacistDetails(Pharmacist user) {
// com.example.ehospital.PharmacistDetails pharmacistDetails = new
// com.example.ehospital.PharmacistDetails();

// pharmacistDetails.setId(user.getId());
// pharmacistDetails.setPhone(user.getPhone());
// pharmacistDetails.setAge(user.getAge());
// pharmacistDetails.setFullNames(user.getFullNames());
// pharmacistDetails.setGender(user.getGender() == Gender.Female ?
// UserGenderType.FEMALE : UserGenderType.MALE);
// pharmacistDetails.setRole(mapUserRoleTypes(user.getRole()));

// return pharmacistDetails;
// }

// public static UserRoleTypes mapUserRoleTypes(UserRoles role) {
// if (role == UserRoles.Patient) {
// return UserRoleTypes.PATIENT;
// }
// if (role == UserRoles.Physician) {
// return UserRoleTypes.PHYSICIAN;
// }
// if (role == UserRoles.Pharmacist) {
// return UserRoleTypes.PHARMACIST;
// }
// return null;
// }
// }
