package com.salus.agenda.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.Patient;

@Repository
public interface PacientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPersonalDataEmail(String email);
}
