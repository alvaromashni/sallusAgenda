package com.salus.agenda.Services;

import org.springframework.stereotype.Service;

import com.salus.agenda.Models.Patient;
import com.salus.agenda.Repositories.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPacient(Patient patient) {
        return patientRepository.save(patient);
    }
}
