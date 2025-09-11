package com.salus.agenda.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salus.agenda.Models.ProfessionalUser;

public interface ProfessionalRepository extends JpaRepository<ProfessionalUser, Long> {

}
