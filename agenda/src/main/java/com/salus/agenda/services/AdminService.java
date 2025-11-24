package com.salus.agenda.services;

import com.salus.agenda.models.Patient;
import com.salus.agenda.models.ProfessionalUser;
import com.salus.agenda.repositories.PatientRepository;
import com.salus.agenda.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public AdminService(PatientRepository patientRepository, ProfessionalRepository professionalRepository) {
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }
    public List<Patient> listAllPatient(){
        return patientRepository.findAll();
    }
    public List<ProfessionalUser> findAllProfessional(){
        return professionalRepository.findAll();
    }
    public void deletePatientById(UUID id){
        patientRepository.deleteById(id);
    }
    public void deleteProfessionalById(UUID id){
        professionalRepository.deleteById(id);
    }

}
