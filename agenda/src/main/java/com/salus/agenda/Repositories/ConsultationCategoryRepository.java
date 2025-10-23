package com.salus.agenda.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salus.agenda.Models.ConsultationCategory;

public interface ConsultationCategoryRepository extends JpaRepository<ConsultationCategory, Long> {
    boolean existsByCategoryName(String categoryName);
}
