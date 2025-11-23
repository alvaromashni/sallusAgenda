package com.salus.agenda.services;

import java.util.UUID;

import com.salus.agenda.exceptions.DataConflictException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.dtos.request.PatientRequestDto;
import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.models.Patient;
import com.salus.agenda.repositories.PatientRepository;

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

    public UUID createPatient(PatientRequestDto requestDto) {

        if (patientRepository.existsByPersonalDataCpf(requestDto.personalData().getCpf())) {
            throw new DataConflictException("This cpf has already been registered!");
        } else if (patientRepository.existsByPersonalDataEmail(requestDto.personalData().getEmail())) {
            throw new DataConflictException("This email has already been registered!");
        } else if (patientRepository.existsByPersonalDataName(requestDto.personalData().getName())) {
            throw new DataConflictException("This name has already been registered!");
        }else if (patientRepository.existsByPersonalData_PhoneNumber(requestDto.personalData().getPhoneNumber())){
            throw new DataConflictException("This phone number has already been registered");
        }
        Patient newPatient = modelMapper.map(requestDto, Patient.class);
        newPatient.getPersonalData().setPassword(passwordEncoder.encode(requestDto.personalData().getPassword()));
        patientRepository.save(newPatient);
        return newPatient.getIdPatient();
    }

    public Patient updatePatientData(UUID id, PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(requestDto.personalData(), patient.getPersonalData());
        return patientRepository.save(patient);
    }

    public void softDeletePatient(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found!");
        }
        patientRepository.softDeleteById(id);
    }

}
