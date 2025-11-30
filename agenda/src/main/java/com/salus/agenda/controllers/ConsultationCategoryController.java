package com.salus.agenda.controllers;

import com.salus.agenda.dtos.response.CategoryResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salus.agenda.models.ConsultationCategory;
import com.salus.agenda.services.ConsultationCategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class ConsultationCategoryController {
    private final ConsultationCategoryService consultationCategoryService;

    public ConsultationCategoryController(ConsultationCategoryService consultationCategoryService) {
        this.consultationCategoryService = consultationCategoryService;
    }

    @PostMapping("/registerCategory")
    public ResponseEntity<?> registerCategory(@RequestBody ConsultationCategory consultation) {
        consultationCategoryService.registerConsultationCategory(consultation);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        consultationCategoryService.hardDeleteConsultationCategory(id);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryResponseDto>> findAll(){
        return ResponseEntity.ok(consultationCategoryService.findAllCategory());
    }


}