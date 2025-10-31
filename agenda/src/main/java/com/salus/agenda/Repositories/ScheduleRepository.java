package com.salus.agenda.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Dtos.Response.ScheduleSummaryDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;

import jakarta.transaction.Transactional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

        boolean existsByProfessionalUser_IdProfessionalUserAndConsultationDate(UUID professionalUserId, LocalDate date);

        @Query("select p.personalData.name as patientName, pu.personalData.name as professionalName, p.personalData.email as patientEmail, pu.personalData.email as professionalUserEmail, s.consultationDate as consultationDate, s.consultationTime as consultationTime, s.consultationDescription as consultationDescription from Schedule s join s.patient p join s.professionalUser pu where pu.idProfessionalUser = :idProfessionalUser and s.consultationDate = :date")
        List<ScheduleSummaryDto> findAllByProfessionalUserAndConsultationDate(
                        @Param("idProfessionalUser") UUID IdprofessionalUser,
                        @Param("date") LocalDate date);

        boolean existsByProfessionalUserAndConsultationDateAndConsultationTime(ProfessionalUser professional,
                        LocalDate date, LocalTime time);

        boolean existsByPatientAndConsultationDateAndConsultationTime(Patient patient, LocalDate date, LocalTime time);

        @Transactional
        @Modifying
        @Query("UPDATE Schedule s SET s.isDeleted = true, s.deletedAt = CURRENT_TIMESTAMP where s.idSchedule=:idSchedule")
        void doSoftDeleteById(@Param("idSchedule") Long id);
}
