package com.salus.agenda.Services;

import org.springframework.stereotype.Service;

import com.salus.agenda.Exceptions.DataConflictException;
import com.salus.agenda.Exceptions.ResourceNotFoundException;
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
