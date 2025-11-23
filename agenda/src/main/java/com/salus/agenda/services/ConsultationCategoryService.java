package com.salus.agenda.services;

import org.springframework.stereotype.Service;

import com.salus.agenda.exceptions.DataConflictException;
import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.models.ConsultationCategory;
import com.salus.agenda.repositories.ConsultationCategoryRepository;

@Service
public class ConsultationCategoryService {
    private final ConsultationCategoryRepository consultationCategoryRepository;

    public ConsultationCategoryService(ConsultationCategoryRepository consultationCategoryRepository) {
        this.consultationCategoryRepository = consultationCategoryRepository;
    }

    public ConsultationCategory registerConsultationCategory(ConsultationCategory consultation) {
        if (consultationCategoryRepository.existsByCategoryName(consultation.getCategoryName())) {
            throw new DataConflictException("Category already exists!");
        }
        ConsultationCategory newCategory = new ConsultationCategory();
        newCategory.setCategoryName(consultation.getCategoryName());
        return consultationCategoryRepository.save(newCategory);
    }

    public void hardDeleteConsultationCategory(Long id) {
        if (!consultationCategoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada!");
        }
        consultationCategoryRepository.deleteById(id);
    }
}
