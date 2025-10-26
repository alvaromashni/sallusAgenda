package com.salus.agenda.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Models.ConsultationCategory;
import com.salus.agenda.Services.ConsultationCategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return ResponseEntity.ok(consultation);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            consultationCategoryService.deleteConsultationCategory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
