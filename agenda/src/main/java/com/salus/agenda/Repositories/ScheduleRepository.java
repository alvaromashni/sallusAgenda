package com.salus.agenda.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByProfessionalUser_IdProfessionalUserAndConsultationDate(Long professionalUserId, LocalDate date);

    List<Schedule> findAllByProfessionalUserAndConsultationDate(ProfessionalUser professionalUser,
            LocalDate date);

}
