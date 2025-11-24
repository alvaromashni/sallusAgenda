package com.salus.agenda.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salus.agenda.models.Patient;

import jakarta.transaction.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByPersonalDataEmail(String email);

    boolean existsByPersonalDataEmail(String email);

    boolean existsByPersonalDataName(String name);

    boolean existsByPersonalDataCpf(String cpf);

    boolean existsByPersonalData_PhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.isDeleted = true, p.deletedAt = CURRENT_TIMESTAMP WHERE p.idPatient = :idPatient")
    void softDeleteById(@Param("idPatient") UUID id);

}
