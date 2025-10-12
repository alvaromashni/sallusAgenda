package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Models.Patient;
import com.salus.agenda.Services.PatientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPatientSchedule(@RequestBody Patient patient) {
        patientService.createPacient(patient);
        return ResponseEntity.ok(patient);
    }

}
