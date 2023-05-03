package com.example.soap.webservices.ehospitalsoap.soap;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.auth.AuthRequest;
import com.example.auth.AuthResponse;
import com.example.auth.AuthorizationHeader;
import com.example.auth.GetAllPharmacistsRequest;
import com.example.auth.GetAllPharmacistsResponse;
import com.example.auth.GetAllPhysiciansRequest;
import com.example.auth.GetAllPhysiciansResponse;
import com.example.auth.PhysicianRegisterRequest;
import com.example.auth.PhysicianRegisterResponse;
import com.example.auth.SelectPhysicianRequest;
import com.example.auth.SelectPhysicianResponse;
import com.example.auth.UserGenderType;
import com.example.auth.PatientRegisterRequest;
import com.example.auth.PatientRegisterResponse;
import com.example.auth.PharmacistRegisterRequest;
import com.example.auth.PharmacistRegisterResponse;
import com.example.auth.UserRoleTypes;
import com.example.soap.webservices.ehospitalsoap.soap.bean.User;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Pharmacist;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Physician;
import com.example.soap.webservices.ehospitalsoap.soap.service.PharmacistService;
import com.example.soap.webservices.ehospitalsoap.soap.service.PhysicianService;
import com.example.soap.webservices.ehospitalsoap.soap.service.PatientService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Endpoint
public class EHospitalEndpoint {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PhysicianService physicianService;

    @Autowired
    private PharmacistService pharmacistService;

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {

        AuthResponse response = new AuthResponse();

        boolean authenticated = patientService.authenticatePatient(request.getUsername(), request.getPassword());
        response.setAuthenticated(authenticated);

        if (!authenticated) {
            response.setMessage("Incorrect credentials");
            return response;
        }

        User existingUser = patientService.findByUsername(request.getUsername());

        Instant now = Instant.now();
        Instant expirationTime = now.plus(10, ChronoUnit.HOURS);
        String secretKey = "mysecretkeywhichmustnotbelessthan256bitslong";
        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(request.getUsername())
                .claim("username", request.getUsername())
                .claim("role", existingUser.getRole().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationTime))
                .signWith(signingKey)
                .compact();

        response.setMessage("Logged in successfully");
        response.setToken(jwtToken);

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "GetAllPhysiciansRequest")
    @ResponsePayload
    public GetAllPhysiciansResponse getAllPhysicians(@RequestPayload GetAllPhysiciansRequest request) {

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        GetAllPhysiciansResponse response = new GetAllPhysiciansResponse();
        List<Physician> users = Arrays.asList(physicianService.getAllphysicians());

        for (Physician user : users) {
            response.getPhysicians().add(mapPhysicianDetails(user));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "GetAllPharmacistsRequest")
    @ResponsePayload
    public GetAllPharmacistsResponse getAllPhysicians(@RequestPayload GetAllPharmacistsRequest request) {

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        GetAllPharmacistsResponse response = new GetAllPharmacistsResponse();
        List<Pharmacist> users = Arrays.asList(pharmacistService.getAllPharmacists());

        for (Pharmacist user : users) {
            response.getPharmacists().add(mapPharmacistDetails(user));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "PatientRegisterRequest")
    @ResponsePayload
    public PatientRegisterResponse registerUser(@RequestPayload PatientRegisterRequest request) {

        // validate request data
        if (request.getFullNames() == "[string]" || request.getFullNames().isEmpty()
                || request.getUsername() == "[string]" || request.getUsername().isEmpty()
                || request.getPassword() == "[string]" || request.getPassword().isEmpty()
                || request.getGender() == null || request.getGender().toString().isEmpty()
                || request.getAge() == 0) {
            throw new RuntimeException("Please fill in all fields correctly");
        }

        User user = new User(request.getFullNames(), request.getUsername(), mapGender(request.getGender()),
                request.getAge(),
                mapUserRoles(UserRoleTypes.PATIENT), request.getPassword());

        Status status = patientService.addUser(user);

        PatientRegisterResponse response = new PatientRegisterResponse();
        response.setRegistered(status == Status.SUCCESS ? true : false);
        response.setMessage("registered successfully");
        response.setUser(mapUser(user));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "PhysicianRegisterRequest")
    @ResponsePayload
    public PhysicianRegisterResponse registerPhysician(@RequestPayload PhysicianRegisterRequest request) {

        // validate request data
        if (request.getFullNames() == "[string]" || request.getFullNames().isEmpty()
                || request.getEmail() == "[string]" || request.getEmail().isEmpty()
                || request.getPassword() == "[string]" || request.getPassword().isEmpty()
                || request.getGender() == null || request.getGender().toString().isEmpty()
                || request.getAge() == 0) {
            throw new RuntimeException("Please fill in all fields correctly");
        }

        Physician physician = new Physician(request.getFullNames(), request.getEmail(),
                mapGender(request.getGender()),
                request.getAge(),
                mapUserRoles(UserRoleTypes.PHYSICIAN), request.getPassword());

        Status status = physicianService.addPhysican(physician);

        PhysicianRegisterResponse response = new PhysicianRegisterResponse();
        response.setRegistered(status == Status.SUCCESS ? true : false);
        response.setMessage("registered successfully");
        response.setPhysician(mapPhysicianDetails(physician));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "PharmacistRegisterRequest")
    @ResponsePayload
    public PharmacistRegisterResponse registerPharmacist(@RequestPayload PharmacistRegisterRequest request) {

        // validate request data
        if (request.getFullNames() == "[string]" || request.getFullNames().isEmpty()
                || request.getPhone() == "[string]" || request.getPhone().isEmpty()
                || request.getPassword() == "[string]" || request.getPassword().isEmpty()
                || request.getGender() == null || request.getGender().toString().isEmpty()
                || request.getAge() == 0) {
            throw new RuntimeException("Please fill in all fields correctly");
        }

        Pharmacist pharmacist = new Pharmacist(request.getFullNames(), request.getPhone(),
                mapGender(request.getGender()),
                request.getAge(),
                mapUserRoles(UserRoleTypes.PHARMACIST), request.getPassword());

        Status status = pharmacistService.addPharmacist(pharmacist);

        PharmacistRegisterResponse response = new PharmacistRegisterResponse();
        response.setRegistered(status == Status.SUCCESS ? true : false);
        response.setMessage("registered successfully");
        response.setPharmacist(mapPharmacistDetails(pharmacist));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/auth", localPart = "SelectPhysicianRequest")
    @ResponsePayload
    public SelectPhysicianResponse selectPhysician(@RequestPayload SelectPhysicianRequest request) throws Exception {

        SelectPhysicianResponse response = new SelectPhysicianResponse();

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        String username = claims.get("username", String.class);

        User user = patientService.findByUsername(username);

        Physician physicianExists = physicianService.findByEmail(request.getPhysicianEmail());

        if (physicianExists == null) {
            throw new RuntimeException("Physician not found");
        }

        User updatedUser = PatientService.selectPhysician(user.getUsername(), physicianExists);

        response.setMessage("selected physician successfully!");
        response.setUser(mapUser(updatedUser));
        response.setSelectedPhysician(mapPhysicianDetails(physicianService.findByEmail(request.getPhysicianEmail())));

        return response;
    }

    private UserRoles mapUserRoles(UserRoleTypes role) {
        if (role == UserRoleTypes.PATIENT) {
            return UserRoles.Patient;
        }
        if (role == UserRoleTypes.PHYSICIAN) {
            return UserRoles.Physician;
        }
        if (role == UserRoleTypes.PHARMACIST) {
            return UserRoles.Pharmacist;
        }
        return null;
    }

    private Gender mapGender(UserGenderType gender) {
        if (gender == UserGenderType.MALE) {
            return Gender.Male;
        }
        return Gender.Female;

    }

    private com.example.auth.PatientDetails mapUser(User user) {
        com.example.auth.PatientDetails PatientDetails = new com.example.auth.PatientDetails();

        PatientDetails.setId(user.getId());
        PatientDetails.setUsername(user.getUsername());
        PatientDetails.setAge(user.getAge());
        PatientDetails.setFullNames(user.getFullNames());
        PatientDetails.setGender(user.getGender() == Gender.Female ? UserGenderType.FEMALE : UserGenderType.MALE);
        PatientDetails.setRole(mapUserRoleTypes(user.getRole()));
        // PatientDetails.setSe

        return PatientDetails;
    }

    private com.example.auth.PhysicianDetails mapPhysicianDetails(Physician user) {
        com.example.auth.PhysicianDetails physicianDetails = new com.example.auth.PhysicianDetails();

        physicianDetails.setId(user.getId());
        physicianDetails.setEmail(user.getEmail());
        physicianDetails.setAge(user.getAge());
        physicianDetails.setFullNames(user.getFullNames());
        physicianDetails.setGender(user.getGender() == Gender.Female ? UserGenderType.FEMALE : UserGenderType.MALE);
        physicianDetails.setRole(mapUserRoleTypes(user.getRole()));

        return physicianDetails;
    }

    private com.example.auth.PharmacistDetails mapPharmacistDetails(Pharmacist user) {
        com.example.auth.PharmacistDetails pharmacistDetails = new com.example.auth.PharmacistDetails();

        pharmacistDetails.setId(user.getId());
        pharmacistDetails.setPhone(user.getPhone());
        pharmacistDetails.setAge(user.getAge());
        pharmacistDetails.setFullNames(user.getFullNames());
        pharmacistDetails.setGender(user.getGender() == Gender.Female ? UserGenderType.FEMALE : UserGenderType.MALE);
        pharmacistDetails.setRole(mapUserRoleTypes(user.getRole()));

        return pharmacistDetails;
    }

    private UserRoleTypes mapUserRoleTypes(UserRoles role) {
        if (role == UserRoles.Patient) {
            return UserRoleTypes.PATIENT;
        }
        if (role == UserRoles.Physician) {
            return UserRoleTypes.PHYSICIAN;
        }
        if (role == UserRoles.Pharmacist) {
            return UserRoleTypes.PHARMACIST;
        }
        return null;
    }

    public static Claims parseJwtToken(String jwtToken) {
        String secretKey = "mysecretkeywhichmustnotbelessthan256bitslong";
        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        // Parse the JWT token and retrieve the claims
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();
        Claims claims = jwtParser.parseClaimsJws(jwtToken).getBody();

        return claims;
    }

}
