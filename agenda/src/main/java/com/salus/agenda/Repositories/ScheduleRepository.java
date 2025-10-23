package com.salus.agenda.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.Patient;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    boolean existsByProfessionalUser_IdProfessionalUserAndConsultationDate(UUID professionalUserId, LocalDate date);
        @Query("select patient.email, professional_user.email, schedule.consultation_date , schedule.consultation_time, schedule.consultation_description from schedule join patient on id_patient = patient_id join professional_user on id_professional_user = professional_user_id")
    List<Schedule> findAllByProfessionalUserAndConsultationDate(ProfessionalUser professionalUser,
            LocalDate date);

    boolean existsByProfessionalUserAndConsultationDateAndConsultationTime(ProfessionalUser professional,
            LocalDate date, LocalTime time);

    boolean existsByPatientAndConsultationDateAndConsultationTime(Patient patient, LocalDate date, LocalTime time);
}
