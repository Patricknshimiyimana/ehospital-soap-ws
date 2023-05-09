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

import com.example.ehospital.AuthRequest;
import com.example.ehospital.AuthResponse;
import com.example.ehospital.AuthorizationHeader;
import com.example.ehospital.GetAllMedecinesRequest;
import com.example.ehospital.GetAllMedecinesResponse;
import com.example.ehospital.GetAllPharmacistsRequest;
import com.example.ehospital.GetAllPharmacistsResponse;
import com.example.ehospital.GetAllPhysiciansRequest;
import com.example.ehospital.GetAllPhysiciansResponse;
import com.example.ehospital.GiveConsultationRequest;
import com.example.ehospital.GiveConsultationResponse;
import com.example.ehospital.MedecineDetails;
import com.example.ehospital.PatientConsultation;
import com.example.ehospital.PhysicianRegisterRequest;
import com.example.ehospital.PhysicianRegisterResponse;
import com.example.ehospital.SelectPhysicianRequest;
import com.example.ehospital.SelectPhysicianResponse;
import com.example.ehospital.UserGenderType;
import com.example.ehospital.PatientRegisterRequest;
import com.example.ehospital.PatientRegisterResponse;
import com.example.ehospital.PharmacistLoginRequest;
import com.example.ehospital.PharmacistLoginResponse;
import com.example.ehospital.PharmacistRegisterRequest;
import com.example.ehospital.PharmacistRegisterResponse;
import com.example.ehospital.PhysicianAuthRequest;
import com.example.ehospital.PhysicianAuthResponse;
import com.example.ehospital.UserRoleTypes;
import com.example.ehospital.AddMedecineRequest;
import com.example.ehospital.AddMedecineResponse;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Consultation;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Patient;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Medecine;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Gender;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.Status;
import com.example.soap.webservices.ehospitalsoap.soap.bean.enums.UserRoles;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Pharmacist;
import com.example.soap.webservices.ehospitalsoap.soap.bean.Physician;
import com.example.soap.webservices.ehospitalsoap.soap.service.PharmacistService;
import com.example.soap.webservices.ehospitalsoap.soap.service.PhysicianService;
import com.example.soap.webservices.ehospitalsoap.soap.service.MedecineService;
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

    @Autowired
    private MedecineService medecineService;

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {

        AuthResponse response = new AuthResponse();

        boolean authenticated = patientService.authenticatePatient(request.getUsername(), request.getPassword());
        response.setAuthenticated(authenticated);

        if (!authenticated) {
            response.setMessage("Incorrect credentials");
            return response;
        }

        Patient existingUser = patientService.findByUsername(request.getUsername());

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

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "PhysicianAuthRequest")
    @ResponsePayload
    public PhysicianAuthResponse authenticatePhysician(@RequestPayload PhysicianAuthRequest request) {

        PhysicianAuthResponse response = new PhysicianAuthResponse();

        boolean authenticated = physicianService.authenticatePhysician(request.getEmail(), request.getPassword());
        response.setAuthenticated(authenticated);

        if (!authenticated) {
            response.setMessage("Incorrect credentials");
            return response;
        }

        Physician existingPhysician = physicianService.findByEmail(request.getEmail());

        Instant now = Instant.now();
        Instant expirationTime = now.plus(10, ChronoUnit.HOURS);
        String secretKey = "mysecretkeywhichmustnotbelessthan256bitslong";
        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(request.getEmail())
                .claim("email", request.getEmail())
                .claim("role", existingPhysician.getRole().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationTime))
                .signWith(signingKey)
                .compact();

        response.setMessage("Logged in successfully");
        response.setToken(jwtToken);

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "PharmacistLoginRequest")
    @ResponsePayload
    public PharmacistLoginResponse authenticatePharmacist(@RequestPayload PharmacistLoginRequest request) {

        PharmacistLoginResponse response = new PharmacistLoginResponse();

        boolean authenticated = pharmacistService.authenticatePharmacist(request.getPhone(), request.getPassword());
        response.setAuthenticated(authenticated);

        if (!authenticated) {
            response.setMessage("Incorrect credentials");
            return response;
        }

        Pharmacist existingPharmacist = pharmacistService.findByPhone(request.getPhone());

        Instant now = Instant.now();
        Instant expirationTime = now.plus(10, ChronoUnit.HOURS);
        String secretKey = "mysecretkeywhichmustnotbelessthan256bitslong";
        Key signingKey = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .setSubject(request.getPhone())
                .claim("phone", request.getPhone())
                .claim("role", existingPharmacist.getRole().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationTime))
                .signWith(signingKey)
                .compact();

        response.setMessage("Logged in successfully");
        response.setToken(jwtToken);

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "GetAllPhysiciansRequest")
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

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "GetAllPharmacistsRequest")
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

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "PatientRegisterRequest")
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

        Patient user = new Patient(request.getFullNames(), request.getUsername(), mapGender(request.getGender()),
                request.getAge(),
                mapUserRoles(UserRoleTypes.PATIENT), request.getPassword());

        Status status = patientService.addUser(user);

        PatientRegisterResponse response = new PatientRegisterResponse();
        response.setRegistered(status == Status.SUCCESS ? true : false);
        response.setMessage("registered successfully");
        response.setUser(mapUser(user));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "PhysicianRegisterRequest")
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

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "PharmacistRegisterRequest")
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

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "SelectPhysicianRequest")
    @ResponsePayload
    public SelectPhysicianResponse selectPhysician(@RequestPayload SelectPhysicianRequest request) throws Exception {

        SelectPhysicianResponse response = new SelectPhysicianResponse();

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        String username = claims.get("username", String.class);

        Patient user = patientService.findByUsername(username);

        Physician physicianExists = physicianService.findByEmail(request.getPhysicianEmail());

        if (physicianExists == null) {
            throw new RuntimeException("Physician not found");
        }

        Patient updatedUser = PatientService.selectPhysician(user.getUsername(), physicianExists);

        response.setMessage("selected physician successfully!");
        response.setUser(mapUser(updatedUser));
        response.setSelectedPhysician(mapPhysicianDetails(physicianService.findByEmail(request.getPhysicianEmail())));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "GiveConsultationRequest")
    @ResponsePayload
    public GiveConsultationResponse giveConsultation(@RequestPayload GiveConsultationRequest request) throws Exception {

        GiveConsultationResponse response = new GiveConsultationResponse();

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        String PhysicianEmail = claims.get("email", String.class);

        Patient user = patientService.findByUsername(request.getPatientUsername());

        if (user == null) {
            throw new RuntimeException("Patient not found");
        }

        if (user.getSelectedPhysician() == null || user.getSelectedPhysician().getEmail() == PhysicianEmail) {
            System.out.println(user.getSelectedPhysician() != null);
            System.out.println(user.getSelectedPhysician().getEmail() == PhysicianEmail);
            throw new RuntimeException("401 - Unauthorized");
        }

        Physician physician = physicianService.findByEmail(PhysicianEmail);

        Consultation consultation = new Consultation(request.getDiseaseName(), physician);

        Patient updatedUser = PatientService.getConsultation(user.getUsername(), consultation);

        response.setMessage("successfully given consultation to patient");
        response.setUser(mapUser(updatedUser));
        response.setConsultation(mapConsultation(consultation));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "AddMedecineRequest")
    @ResponsePayload
    public AddMedecineResponse addMedecine(@RequestPayload AddMedecineRequest request) {

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        String pharmacistPhone = claims.get("phone", String.class);

        Pharmacist pharmacist = pharmacistService.findByPhone(pharmacistPhone);

        if (pharmacist == null) {
            throw new RuntimeException("Unauthorized");
        }

        // validate request data
        if (request.getMedName() == "[string]" || request.getMedName().isEmpty()
                || request.getMedPrice() == 0
                || request.getExpirationDate().equals("")) {
            throw new RuntimeException("Please fill in all fields correctly");
        }

        Medecine newMed = new Medecine(request.getMedName(), request.getMedPrice(),
                request.getExpirationDate().toString());

        Status status = medecineService.addMedecine(newMed);

        AddMedecineResponse response = new AddMedecineResponse();
        response.setAdded(status == Status.SUCCESS ? true : false);
        response.setMessage("Medecine added successfuly");
        response.setMedecine(mapMed(newMed));

        return response;
    }

    @PayloadRoot(namespace = "http://example.com/ehospital", localPart = "GetAllMedecinesRequest")
    @ResponsePayload
    public GetAllMedecinesResponse getMedecine(@RequestPayload GetAllMedecinesRequest request) {

        AuthorizationHeader authHeader = request.getAuthorizationHeader();
        String token = authHeader.getToken();
        System.out.println("ahhahahahah" + token);

        Claims claims = parseJwtToken(token);
        System.out.println("claimsssss" + claims);

        String pharmacistPhone = claims.get("phone", String.class);

        Pharmacist pharmacist = pharmacistService.findByPhone(pharmacistPhone);

        if (pharmacist == null) {
            throw new RuntimeException("Unauthorized");
        }

        GetAllMedecinesResponse response = new GetAllMedecinesResponse();

        List<Medecine> availableMeds = Arrays.asList(medecineService.getMedicines());

        for (Medecine med : availableMeds) {
            response.getMedecines().add(mapMed(med));
        }
        return response;
    }

    private PatientConsultation mapConsultation(Consultation consultation) {
        PatientConsultation patientConsultation = new PatientConsultation();
        patientConsultation.setDiseaseName(consultation.getDisease());

        return patientConsultation;
    }

    private MedecineDetails mapMed(Medecine med) {
        MedecineDetails newMed = new MedecineDetails();

        double medPrice = med.getMedPrice();
        // med.getMedPrice() returns a double convert it to string
        String medPriceString = String.valueOf(medPrice);

        newMed.setMedName(med.getMedName());
        newMed.setMedPrice(medPriceString);
        newMed.setExpirationDate(med.getExpirationDate());

        return newMed;
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

    private com.example.ehospital.PatientDetails mapUser(Patient user) {
        com.example.ehospital.PatientDetails PatientDetails = new com.example.ehospital.PatientDetails();

        PatientDetails.setId(user.getId());
        PatientDetails.setUsername(user.getUsername());
        PatientDetails.setAge(user.getAge());
        PatientDetails.setFullNames(user.getFullNames());
        PatientDetails.setGender(user.getGender() == Gender.Female ? UserGenderType.FEMALE : UserGenderType.MALE);
        PatientDetails.setRole(mapUserRoleTypes(user.getRole()));
        // PatientDetails.setSe

        return PatientDetails;
    }

    private com.example.ehospital.PhysicianDetails mapPhysicianDetails(Physician user) {
        com.example.ehospital.PhysicianDetails physicianDetails = new com.example.ehospital.PhysicianDetails();

        physicianDetails.setId(user.getId());
        physicianDetails.setEmail(user.getEmail());
        physicianDetails.setAge(user.getAge());
        physicianDetails.setFullNames(user.getFullNames());
        physicianDetails.setGender(user.getGender() == Gender.Female ? UserGenderType.FEMALE : UserGenderType.MALE);
        physicianDetails.setRole(mapUserRoleTypes(user.getRole()));

        return physicianDetails;
    }

    private com.example.ehospital.PharmacistDetails mapPharmacistDetails(Pharmacist user) {
        com.example.ehospital.PharmacistDetails pharmacistDetails = new com.example.ehospital.PharmacistDetails();

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
