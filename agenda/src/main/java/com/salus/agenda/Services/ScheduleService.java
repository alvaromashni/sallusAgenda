package com.salus.agenda.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.ScheduleRequestDto;
import com.salus.agenda.Exceptions.ResourceNotFoundException;
import com.salus.agenda.Exceptions.ScheduleConflictException;
import com.salus.agenda.Models.ConsultationCategory;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;
import com.salus.agenda.Repositories.ConsultationCategoryRepository;
import com.salus.agenda.Repositories.PatientRepository;
import com.salus.agenda.Repositories.ProfessionalRepository;
import com.salus.agenda.Repositories.ScheduleRepository;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ProfessionalRepository professionalRepository;
    private final PatientRepository patientRepository;
    private final ConsultationCategoryRepository consultationCategoryRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, ProfessionalRepository professionalRepository,
            PatientRepository patientRepository, ConsultationCategoryRepository consultationCategoryRepository) {
        this.scheduleRepository = scheduleRepository;
        this.professionalRepository = professionalRepository;
        this.patientRepository = patientRepository;
        this.consultationCategoryRepository = consultationCategoryRepository;

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
        ConsultationCategory consultationCategory = consultationCategoryRepository
                .findById(newSchedule.consultationCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation category not found!"));
        if (hasConflict(professional, newSchedule.consultationDate(), newSchedule.consultationTime())) {
            throw new ScheduleConflictException("This time slot is already booked!");
        }
        if (patientHasConflict(patient, newSchedule.consultationDate(), newSchedule.consultationTime())) {
            throw new ScheduleConflictException("The patient already has an appointment at this time!");
        }
        Schedule schedule = new Schedule(
                newSchedule.consultationDate(),
                newSchedule.consultationDescription(),
                consultationCategory,
                patient,
                professional,
                newSchedule.consultationTime());
        return scheduleRepository.save(schedule);
    }

    public Map<LocalDate, Boolean> isSchedulesForTheDay(UUID professionalUserId, LocalDate day) {
        ProfessionalUser professionalUser = professionalRepository.findById(professionalUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        Map<LocalDate, Boolean> map = new HashMap<>();
        for(int i =1; i <= day.lengthOfMonth(); i++) {
            LocalDate currentDate = day.withDayOfMonth(i);
            boolean verdadeiro = scheduleRepository.existsByProfessionalUser_IdProfessionalUserAndConsultationDate(professionalUserId,
                    currentDate);
            map.put(currentDate, verdadeiro);
        }
        return map;
        
    }

    //public List<Schedule> getSchedulesForTheDay(ProfessionalUser professionalUser, LocalDate date) {
        //ProfessionalUser profUser = professionalRepository.findById(professionalUser.getIdProfessionalUser())
                //.orElseThrow(() -> new ResourceNotFoundException("Professional not found!"));
        //return scheduleRepository.findAllByProfessionalUserAndConsultationDate(professionalUser, date);
    //}
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found!"));
        scheduleRepository.delete(schedule);
    }
}
