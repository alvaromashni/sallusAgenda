package com.salus.agenda.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.ProfessionalUser;

@Repository
public interface ProfessionalRepository extends JpaRepository<ProfessionalUser, UUID> {
    Optional<ProfessionalUser> findByPersonalDataEmail(String email);

    boolean existsByPersonalDataEmail(String email);

    boolean existsByPersonalDataName(String name);

    boolean existsByPersonalDataCpf(String cpf);

    boolean existsByCrm(String crm);
}
