package com.salus.agenda.services;


import com.salus.agenda.dtos.response.ConsultationLinkResponseDto;
import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.models.ConsultationLink;
import com.salus.agenda.models.ProfessionalUser;
import com.salus.agenda.repositories.ConsultationLinkRepository;
import com.salus.agenda.repositories.PatientRepository;
import com.salus.agenda.repositories.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConsultationLinkService {
    private final ProfessionalRepository professionalRepository;
    private final ConsultationLinkRepository consultationLinkRepository;

    public ConsultationLinkService(ProfessionalRepository professionalRepository, PatientRepository patientRepository, ConsultationLinkRepository consultationLinkRepository) {
        this.professionalRepository = professionalRepository;
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
    public UUID validateLink(UUID id){
        ConsultationLink consultationLink = consultationLinkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link not found!"));
        if (consultationLink.getExpirationTime().isBefore(LocalDateTime.now())){
            consultationLinkRepository.disableLink(id);
            throw new IllegalStateException("This link has expired!");
        }
        if (!consultationLink.isActive()){
            throw new IllegalStateException("This link is not active");
        }
        return consultationLink.getProfessionalUser().getIdProfessionalUser();
    }
    public void disableLink(UUID id){
        consultationLinkRepository.disableLink(id);
    }

    
}
