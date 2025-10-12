package com.salus.agenda.Services;

import java.time.LocalDate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salus.agenda.Dtos.ScheduleRequestDto;
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

    public Schedule registerSchedule(ScheduleRequestDto newSchedule) {
        Schedule schedule = new Schedule();
        ProfessionalUser professional = professionalRepository.findById(newSchedule.professionalUserId())
                .orElseThrow(() -> new RuntimeException("Professional not found!"));
        Patient patient = patientRepository.findById(newSchedule.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found!"));
        ConsultationCategory consultationCategory = consultationCategoryRepository
                .findById(newSchedule.consultationCategoryId())
                .orElseThrow(() -> new RuntimeException("Consultation category not found!"));
        schedule.setConsultationCategory(consultationCategory);
        schedule.setConsultationDate(newSchedule.consultationDate());
        schedule.setConsultationTime(newSchedule.consultationTime());
        schedule.setPatient(patient);
        schedule.setProfessionalUser(professional);
        return scheduleRepository.save(schedule);
    }

    public boolean hasSchedulesForTheDay(Long professionalUserId, LocalDate day) {
        return scheduleRepository.existsByProfessionalUser_IdProfessionalUserAndConsultationDate(professionalUserId,
                day);
    }

    public List<Schedule> getSchedulesForTheDay(ProfessionalUser professionalUser, LocalDate date) {
        return scheduleRepository.findAllByProfessionalUserAndConsultationDate(professionalUser, date);
    }
}
