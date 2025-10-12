package com.salus.agenda.Services;

import org.springframework.stereotype.Service;

import com.salus.agenda.Models.ConsultationCategory;
import com.salus.agenda.Repositories.ConsultationCategoryRepository;

@Service
public class ConsultationCategoryService {
    private final ConsultationCategoryRepository consultationCategoryRepository;

    public ConsultationCategoryService(ConsultationCategoryRepository consultationCategoryRepository) {
        this.consultationCategoryRepository = consultationCategoryRepository;
    }

    public ConsultationCategory registerConsultationCategory(ConsultationCategory consultation) {
        if (consultationCategoryRepository.existsByCategoryName(consultation.getCategoryName())) {
            throw new RuntimeException("Category already exists!");
        }
        ConsultationCategory newCategory = new ConsultationCategory();
        newCategory.setCategoryName(consultation.getCategoryName());
        return consultationCategoryRepository.save(newCategory);
    }
}
