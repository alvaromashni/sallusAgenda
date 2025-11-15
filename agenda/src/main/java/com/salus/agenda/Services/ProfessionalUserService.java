package com.salus.agenda.Services;

import java.time.LocalTime;
import java.util.*;

import com.salus.agenda.Dtos.Request.HoursDto;
import com.salus.agenda.Models.Hours;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.Request.ProfessionalRequestDto;
import com.salus.agenda.Exceptions.DataConflictException;
import com.salus.agenda.Exceptions.ResourceNotFoundException;
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
            throw new DataConflictException("This crm has already been registered");
        } else if (professionalRepository.existsByPersonalDataCpf(professional.personalData().getCpf())) {
            throw new DataConflictException("This cpf has already been registered");
        } else if (professionalRepository.existsByPersonalDataEmail(professional.personalData().getEmail())) {
            throw new DataConflictException("This email has already been registered!");
        } else if (professionalRepository.existsByPersonalDataName(professional.personalData().getName())) {
            throw new DataConflictException("This name has already been registered");
        }
        ProfessionalUser newProfessional = modelMapper.map(professional, ProfessionalUser.class);
        newProfessional.getPersonalData()
                .setPassword(passwordEncoder.encode(professional.personalData().getPassword()));
        return professionalRepository.save(newProfessional);
    }

    public ProfessionalUser uptadeProfessionalData(UUID professionalId, ProfessionalRequestDto professionalDto) {
        ProfessionalUser professionalUser = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(professionalDto, professionalUser);
        if (professionalDto.personalData() != null) {
            modelMapper.map(professionalDto.personalData(), professionalUser.getPersonalData());
        }
        return professionalRepository.save(professionalUser);
    }

    public void softDeleteProfessional(UUID id) {
        if (!professionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
    professionalRepository.softDeleteById(id);
    }
    public ProfessionalUser updateProfessionalHours(UUID id, HoursDto dto){
        ProfessionalUser professionalUser = professionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado!"));
        Hours hours = professionalUser.getHours();
        if (professionalUser.getHours() == null){
            hours = new Hours();
            hours.setHours(new LinkedHashSet<>());
            professionalUser.setHours(hours);
        }

        Set<LocalTime>updatedHours = new LinkedHashSet<>(hours.getHours());
        updatedHours.addAll(dto.hours());
        hours.setHours(updatedHours);
        professionalUser.setIdProfessionalUser(id);
        return professionalRepository.save(professionalUser);
    }
    public ProfessionalUser deleteProfessionalHours(UUID id, HoursDto dto){
        ProfessionalUser professionalUser = professionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        Hours hours = professionalUser.getHours();
        Set<LocalTime> updatedHours = new LinkedHashSet<>(hours.getHours());
        updatedHours.remove(dto.hours());
        hours.setHours(updatedHours);
        return professionalRepository.save(professionalUser);
    }
}
