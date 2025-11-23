package com.salus.agenda.controllers;

import com.salus.agenda.models.Patient;
import com.salus.agenda.models.ProfessionalUser;
import com.salus.agenda.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listAllProfessional")
    public ResponseEntity<List<ProfessionalUser>> listAllProfessional() {
        List<ProfessionalUser> professionals = adminService.findAllProfessional();
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/listAllPatients")
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = adminService.listAllPatient();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        adminService.deletePatientById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/deleteProfessional/{id}")
    public ResponseEntity<?> deleteProfessional(@PathVariable UUID id) {
        adminService.deleteProfessionalById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
