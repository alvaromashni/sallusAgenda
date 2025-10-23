package com.salus.agenda.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;
import com.salus.agenda.Dtos.ScheduleSummaryDto;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    boolean existsByProfessionalUser_IdProfessionalUserAndConsultationDate(UUID professionalUserId, LocalDate date);

    @Query(nativeQuery = true, value = "select p.name, p.email, pu.name, pu.email, s.consultation_date , s.consultation_time, s.consultation_description from schedule s join patient p on p.id_patient = s.patient_id join professional_user pu on pu.id_professional_user = s.professional_user_id where s.professional_user_id = :idProfessionalUser and s.consultation_date = :date")
    List<ScheduleSummaryDto> findAllByProfessionalUserAndConsultationDate(
            @Param("idProfessionalUser") UUID IdprofessionalUser,
            @Param("date") LocalDate date);

    boolean existsByProfessionalUserAndConsultationDateAndConsultationTime(ProfessionalUser professional,
            LocalDate date, LocalTime time);

    boolean existsByPatientAndConsultationDateAndConsultationTime(Patient patient, LocalDate date, LocalTime time);
}
