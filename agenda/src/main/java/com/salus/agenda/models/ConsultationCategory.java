package com.salus.agenda.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ConsultationCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConsultationCategory;
    @NotBlank(message = "The field category name can not be blank")
    @Column(unique = true, nullable = false)
    private String categoryName;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "consultationCategory")
    @JsonIgnore
    private List<Schedule> schedules;

    public ConsultationCategory(String categoryName, List<Schedule> schedules, LocalDateTime createdAt,
            Boolean isDeleted, LocalDateTime deletedAt) {
        this.categoryName = categoryName;
        this.schedules = schedules;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;

    }

    public ConsultationCategory() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public long getIdConsultationCategory() {
        return idConsultationCategory;
    }

    public void setIdConsultationCategory(long idConsultationCategory) {
        this.idConsultationCategory = idConsultationCategory;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
