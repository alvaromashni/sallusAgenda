package com.salus.agenda.Services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.PatientRequestDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.PersonalData;
import com.salus.agenda.Repositories.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder,
            ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Patient createPacient(PatientRequestDto requestDto) {
        Patient newPatient = modelMapper.map(requestDto, Patient.class);
        newPatient.getPersonalData().setPassword(passwordEncoder.encode(requestDto.personalData().getPassword()));
        return patientRepository.save(newPatient);
    }
}
