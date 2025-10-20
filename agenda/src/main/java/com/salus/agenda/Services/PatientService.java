package com.salus.agenda.Services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.PatientRequestDto;
import com.salus.agenda.Exceptions.ResourceNotFoundException;
import com.salus.agenda.Models.Patient;
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

        if (patientRepository.existsByPersonalDataCpf(requestDto.personalData().getCpf())) {
            throw new RuntimeException("This cpf has already been registered!");
        } else if (patientRepository.existsByPersonalDataEmail(requestDto.personalData().getEmail())) {
            throw new RuntimeException("This email has already been registered!");
        } else if (patientRepository.existsByPersonalDataName(requestDto.personalData().getName())) {
            throw new RuntimeException("This name has already been registered!");
        }
        Patient newPatient = modelMapper.map(requestDto, Patient.class);
        newPatient.getPersonalData().setPassword(passwordEncoder.encode(requestDto.personalData().getPassword()));
        return patientRepository.save(newPatient);
    }

    public Patient updatePatientData(Long id, PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(requestDto.personalData(), patient.getPersonalData());
        return patientRepository.save(patient);
    }
}
