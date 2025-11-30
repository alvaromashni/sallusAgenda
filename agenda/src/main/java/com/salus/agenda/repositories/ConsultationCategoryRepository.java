package com.salus.agenda.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salus.agenda.models.ConsultationCategory;

public interface ConsultationCategoryRepository extends JpaRepository<ConsultationCategory, Long> {
    boolean existsByCategoryName(String categoryName);
    ConsultationCategory findByCategoryName(String categoryName);
}
