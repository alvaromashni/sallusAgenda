package com.salus.agenda.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.exceptions.ScheduleConflictException;
import com.salus.agenda.models.Patient;
import com.salus.agenda.models.ProfessionalUser;
import com.salus.agenda.models.Schedule;
import com.salus.agenda.repositories.ConsultationCategoryRepository;
import com.salus.agenda.repositories.PatientRepository;
import com.salus.agenda.repositories.ProfessionalRepository;
import com.salus.agenda.repositories.ScheduleRepository;
import com.salus.agenda.dtos.request.ScheduleRequestDto;
import com.salus.agenda.dtos.response.ScheduleSummaryDto;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ProfessionalRepository professionalRepository;
    private final PatientRepository patientRepository;
    private final ConsultationCategoryRepository consultationCategoryRepository;
    private final ModelMapper modelMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ProfessionalRepository professionalRepository,
            PatientRepository patientRepository, ConsultationCategoryRepository consultationCategoryRepository,
            ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.professionalRepository = professionalRepository;
        this.patientRepository = patientRepository;
        this.consultationCategoryRepository = consultationCategoryRepository;
        this.modelMapper = modelMapper;

    }

    public boolean hasConflict(ProfessionalUser professionalUser, LocalDate date, LocalTime time) {
        return scheduleRepository.existsByProfessionalUserAndConsultationDateAndConsultationTime(professionalUser,
                date, time);
    }

    public boolean patientHasConflict(Patient patient, LocalDate date, LocalTime time) {
        return scheduleRepository.existsByPatientAndConsultationDateAndConsultationTime(patient, date, time);
    }

    public Schedule registerSchedule(ScheduleRequestDto newSchedule) {

        ProfessionalUser professional = professionalRepository.findById(newSchedule.professionalUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        Patient patient = patientRepository.findById(newSchedule.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        if (!consultationCategoryRepository.existsById(newSchedule.consultationCategoryId())) {
            throw new ResourceNotFoundException("Categoria não encontrada!");
        }
        if (hasConflict(professional, newSchedule.consultationDate(), newSchedule.consultationTime())) {
            throw new ScheduleConflictException("This time slot is already booked!");
        }
        if (patientHasConflict(patient, newSchedule.consultationDate(), newSchedule.consultationTime())) {
            throw new ScheduleConflictException("O paciente já tem um agendamento para esta data e horário!");
        }
        Schedule schedule = modelMapper.map(newSchedule, Schedule.class);
        return scheduleRepository.save(schedule);
    }

    public Map<LocalDate, Boolean> isScheduledForTheDay(UUID professionalUserId, LocalDate day) {
        if (!professionalRepository.existsById(professionalUserId)) {
            throw new ResourceNotFoundException("Profissional não encontrado!");
        }
        Map<LocalDate, Boolean> map = new HashMap<>();
        for (int i = 1; i <= day.lengthOfMonth(); i++) {
            LocalDate currentDate = day.withDayOfMonth(i);
            boolean verdadeiro = scheduleRepository.existsByProfessionalUser_IdProfessionalUserAndConsultationDate(
                    professionalUserId,
                    currentDate);
            map.put(currentDate, verdadeiro);
        }
        return map;

    }

    public List<ScheduleSummaryDto> getSchedulesForTheDay(UUID professionalUserId, LocalDate date) {
        if (!professionalRepository.existsById(professionalUserId)) {
            throw new ResourceNotFoundException("Profissional não encontrado!");
        }
        return scheduleRepository.findAllByProfessionalUserAndConsultationDate(professionalUserId, date);
    }

    public void hardDeleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado!");
        }
        scheduleRepository.deleteById(id);
    }

    public void softDeleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado!");
        }
        scheduleRepository.softDeleteById(id);
    }
}
