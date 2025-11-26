package com.salus.agenda.services;

import java.time.LocalTime;
import java.util.*;

import com.salus.agenda.dtos.request.HoursRequestDto;
import com.salus.agenda.dtos.response.ProfessionalDataResponseDto;
import com.salus.agenda.dtos.response.ProfessionalResponseDto;
import com.salus.agenda.models.Hours;
import com.salus.agenda.repositories.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salus.agenda.dtos.request.ProfessionalRequestDto;
import com.salus.agenda.exceptions.DataConflictException;
import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.models.ProfessionalUser;
import com.salus.agenda.repositories.ProfessionalRepository;

@Service
public class ProfessionalUserService {
    private static final Logger log = LoggerFactory.getLogger(ProfessionalUserService.class);
    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ScheduleRepository scheduleRepository;

    public ProfessionalUserService(ProfessionalRepository professionalRepository, ModelMapper modelMapper,
                                   PasswordEncoder passwordEncoder, ScheduleRepository scheduleRepository) {
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.scheduleRepository = scheduleRepository;
    }

    public ProfessionalUser registerProfessionalUser(ProfessionalRequestDto professional) {
        if (professionalRepository.existsByCrm(professional.crm())) {
            throw new DataConflictException("This crm has already been registered");
        } else if (professionalRepository.existsByPersonalDataCpf(professional.personalData().getCpf())) {
            throw new DataConflictException("This cpf has already been registered");
        } else if (professionalRepository.existsByPersonalDataEmail(professional.personalData().getEmail())) {
            throw new DataConflictException("This email has already been registered!");
        } else if (professionalRepository.existsByPersonalData_PhoneNumber(professional.personalData().getPhoneNumber())) {
            throw new DataConflictException("This phone number has already been registered");
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
    public ProfessionalUser updateProfessionalHours(UUID id, HoursRequestDto dto){
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
    public ProfessionalUser deleteProfessionalHours(UUID id, LocalTime hour){
        ProfessionalUser professionalUser = professionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        Hours hours = professionalUser.getHours();
        Set<LocalTime> updatedHours = new LinkedHashSet<>(hours.getHours());
        updatedHours.remove(hour);
        hours.setHours(updatedHours);
        return professionalRepository.save(professionalUser);
    }
    public Map<LocalTime, Boolean> findAllProfessionalHours(UUID id){
        ProfessionalUser professionalUser = professionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        Set<LocalTime> hours = professionalUser.getHours().getHours();
        Map<LocalTime, Boolean> map = new HashMap<>();
        for (LocalTime hour: hours) {
            boolean avaliable = scheduleRepository.existsByConsultationTime(hour);
            map.put(hour, avaliable);
        }
        return map;
    }
    public List<ProfessionalResponseDto> listAllProfessional(){
        return professionalRepository.findAll()
                .stream()
                .map(professional -> new ProfessionalResponseDto(professional.getPersonalData().getName(), professional.getExpertise(), professional.getOccupation()))
                .toList();

    }
    public ProfessionalDataResponseDto findProfessionalData(UUID id){
        ProfessionalUser professionalUser = professionalRepository
                .findById(id).orElseThrow(()->new ResourceNotFoundException("Professional not found!"));
        return new ProfessionalDataResponseDto(professionalUser.getPersonalData());
    }
}
