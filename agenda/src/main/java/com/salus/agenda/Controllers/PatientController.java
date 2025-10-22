package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Dtos.PatientRequestDto;
import com.salus.agenda.Dtos.PatientResponseDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Services.PatientService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final ModelMapper modelMapper;

    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPatientSchedule(@RequestBody @Valid PatientRequestDto patient) {
        try {
            patientService.createPacient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(patient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePatientData(@PathVariable Long id, @RequestBody PatientRequestDto patientDto) {
        try {
            Patient updatedPatient = patientService.updatePatientData(id, patientDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
