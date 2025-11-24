package com.salus.agenda.controllers.auth;

import com.salus.agenda.dtos.request.PatientRequestLoginDto;
import com.salus.agenda.dtos.response.PatientLoginResponseDto;
import com.salus.agenda.models.Patient;
import com.salus.agenda.services.auth.PatientAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/patient")
public class PatientAuthController {
    private final PatientAuthService patientAuthService;

    public PatientAuthController(PatientAuthService patientAuthService){
        this.patientAuthService = patientAuthService;
    }
    @PostMapping("/login")
    public ResponseEntity<PatientLoginResponseDto> login (@Valid @RequestBody PatientRequestLoginDto dto){
        String token = patientAuthService.login(dto);
        Patient patient = patientAuthService.loadByEmail(dto.email());
        return ResponseEntity.ok(new PatientLoginResponseDto(patient.getIdPatient(), token));
    }
}
