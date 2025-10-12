package com.salus.agenda.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;
import com.salus.agenda.Repositories.ScheduleRepository;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public boolean hasSchedulesForTheDay(ProfessionalUser professionalUser, LocalDate day) {
        return scheduleRepository.existsByProfessionalUserAndConsultationDateBetween(professionalUser, day);
    }

    public List<Schedule> getSchedulesForTheDay(ProfessionalUser professionalUser, LocalDate date) {
        return scheduleRepository.findAllByProfessionalUserAndConsultationDateBetween(professionalUser, date);
    }
}
