package com.salus.agenda.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salus.agenda.Models.ProfessionalUser;

import jakarta.transaction.Transactional;

@Repository
public interface ProfessionalRepository extends JpaRepository<ProfessionalUser, UUID> {
    Optional<ProfessionalUser> findByPersonalDataEmail(String email);

    boolean existsByPersonalDataEmail(String email);

    boolean existsByPersonalDataName(String name);

    boolean existsByPersonalDataCpf(String cpf);

    boolean existsByCrm(String crm);

    @Transactional
    @Modifying
    @Query("UPDATE ProfessionalUser pu SET pu.isDeleted = true, pu.deletedAt = CURRENT_TIMESTAMP where pu.idProfessionalUser = :idProfessionalUser")
    void softDeleteById(@Param("idProfessionalUser") UUID id);
}
