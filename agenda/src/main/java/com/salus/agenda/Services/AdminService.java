package com.salus.agenda.Services;

import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Repositories.PatientRepository;
import com.salus.agenda.Repositories.ProfessionalRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
