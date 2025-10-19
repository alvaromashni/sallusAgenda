package com.salus.agenda.Services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.ProfessionalRequestDto;
import com.salus.agenda.Exceptions.ProfessionalConflictException;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Repositories.ProfessionalRepository;

@Service
public class ProfessionalUserService {
    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ProfessionalUserService(ProfessionalRepository professionalRepository, ModelMapper modelMapper,
            PasswordEncoder passwordEncoder) {
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfessionalUser registerProfessionalUser(ProfessionalRequestDto professional) {
        if (professionalRepository.existsByCrm(professional.crm())) {
            throw new ProfessionalConflictException("This crm has already been registered");
        } else if (professionalRepository.existsByPersonalDataCpf(professional.personalData().getCpf())) {
            throw new ProfessionalConflictException("This cpf has already been registered");
        } else if (professionalRepository.existsByPersonalDataEmail(professional.personalData().getEmail())) {
            throw new ProfessionalConflictException("This email has already been registered!");
        } else if (professionalRepository.existsByPersonalDataName(professional.personalData().getName())) {
            throw new ProfessionalConflictException("This name has already been registered");
        }
        ProfessionalUser newProfessional = modelMapper.map(professional, ProfessionalUser.class);
        newProfessional.getPersonalData()
                .setPassword(passwordEncoder.encode(professional.personalData().getPassword()));
        return professionalRepository.save(newProfessional);
    }

    public ProfessionalUser uptadeProfessionalData(Long professionalId, ProfessionalUser updatedProfessional) {
        Optional<ProfessionalUser> professionalUser = professionalRepository.findById(professionalId);
        if (professionalUser.isEmpty()) {
        }

        return new ProfessionalUser();
    }
}
