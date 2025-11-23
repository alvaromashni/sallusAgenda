package com.salus.agenda.Controllers.Auth;

import com.salus.agenda.Dtos.Request.PatientRequestLoginDto;
import com.salus.agenda.Dtos.Response.PatientLoginResponseDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Services.Auth.PatientAuthService;
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
