package com.salus.agenda.repositories;

import com.salus.agenda.models.ConsultationLink;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ConsultationLinkRepository extends JpaRepository<ConsultationLink, UUID> {
    @Transactional
    @Modifying
    @Query("update ConsultationLink set isActive = false where idConsultationLink= :id")
    void disableLink(@Param("id")UUID id);
}
