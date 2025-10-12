package com.salus.agenda.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByProfessionalUserAndConsultationDateBetween(ProfessionalUser professionalUser, LocalDate date);

    List<Schedule> findAllByProfessionalUserAndConsultationDateBetween(ProfessionalUser professionalUser,
            LocalDate date);
}
