package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Dtos.Request.PatientRequestDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Services.PatientService;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPatientSchedule(@RequestBody @Valid PatientRequestDto patient) {
        patientService.createPacient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePatientData(@PathVariable UUID id, @RequestBody PatientRequestDto patientDto) {
        Patient updatedPatient = patientService.updatePatientData(id, patientDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
    }

    @DeleteMapping("soft-delete/{id}")
    public ResponseEntity<?> softDeletePatient(@PathVariable UUID id, @RequestBody String entity) {

        patientService.softDeletePatient(id);
        return ResponseEntity.ok().build();
    }
}
