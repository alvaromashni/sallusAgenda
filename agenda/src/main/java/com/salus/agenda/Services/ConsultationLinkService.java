package com.salus.agenda.Services;

import com.salus.agenda.Dtos.Request.ConsultationLinkRequestDto;
import com.salus.agenda.Dtos.Response.ConsultationLinkResponseDto;
import com.salus.agenda.Exceptions.ResourceNotFoundException;
import com.salus.agenda.Models.ConsultationLink;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Repositories.ConsultationLinkRepository;
import com.salus.agenda.Repositories.PatientRepository;
import com.salus.agenda.Repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class ConsultationLinkService {
    private final ProfessionalRepository professionalRepository;
    private final PatientRepository patientRepository;
    private final ConsultationLinkRepository consultationLinkRepository;

    public ConsultationLinkService(ProfessionalRepository professionalRepository, PatientRepository patientRepository, ConsultationLinkRepository consultationLinkRepository) {
        this.professionalRepository = professionalRepository;
        this.patientRepository = patientRepository;
        this.consultationLinkRepository = consultationLinkRepository;
    }
    @Value("${frontend.url}")
    private String url;
    public ConsultationLinkResponseDto generateLink(UUID id){
        ProfessionalUser professionalUser = professionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professional not found"));
        ConsultationLink consultationLink = new ConsultationLink();
        consultationLink.setProfessionalUser(professionalUser);
        consultationLink.setUrl(url);
        consultationLink.setExpirationTime(LocalDateTime.now().plusMinutes(20));
        consultationLinkRepository.save(consultationLink);
        return new ConsultationLinkResponseDto(consultationLink.getUrl() + "/" + consultationLink.getIdConsultationLink());
    }
    public void validateLink(UUID id){
        ConsultationLink consultationLink = consultationLinkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link not found!"));
        if (consultationLink.getExpirationTime().isBefore(LocalDateTime.now())){
            consultationLinkRepository.disableLink(id);
            throw new IllegalStateException("This link has expired!");
        }
        if (!consultationLink.isActive()){
            throw new IllegalStateException("This link is not active");
        }
    }
    public void disableLink(UUID id){
        consultationLinkRepository.disableLink(id);
    }

    
}
