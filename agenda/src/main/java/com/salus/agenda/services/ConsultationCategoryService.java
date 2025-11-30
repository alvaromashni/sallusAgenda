package com.salus.agenda.services;

import com.salus.agenda.dtos.response.CategoryResponseDto;
import org.springframework.stereotype.Service;

import com.salus.agenda.exceptions.DataConflictException;
import com.salus.agenda.exceptions.ResourceNotFoundException;
import com.salus.agenda.models.ConsultationCategory;
import com.salus.agenda.repositories.ConsultationCategoryRepository;

import java.util.List;

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
    public List<CategoryResponseDto> findAllCategory(){
        return consultationCategoryRepository.findAll().stream()
                .map(category -> new CategoryResponseDto(category.getIdConsultationCategory(), category.getCategoryName()))
                .toList();

    }
}
