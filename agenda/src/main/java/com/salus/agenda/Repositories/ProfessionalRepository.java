package com.salus.agenda.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.ProfessionalUser;

@Repository
public interface ProfessionalRepository extends JpaRepository<ProfessionalUser, Long> {
    Optional<ProfessionalUser> findByPersonalDataEmail(String email);
}
