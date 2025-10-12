package com.salus.agenda.Services;

import org.springframework.stereotype.Service;
import com.salus.agenda.Repositories.ProfessionalRepository;

@Service
public class ProfessionalUserService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalUserService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }
}
