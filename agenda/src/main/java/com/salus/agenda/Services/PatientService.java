package com.salus.agenda.Services;

import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.PatientRequestDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Repositories.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPacient(PatientRequestDto requestDto) {
        Patient newPatient = new Patient();
        newPatient.setPersonalData();
        return patientRepository.save(patient);
    }
}
