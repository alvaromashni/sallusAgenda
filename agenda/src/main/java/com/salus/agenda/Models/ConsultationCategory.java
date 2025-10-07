package com.salus.agenda.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ConsultationCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConsultationCategory;
    private String categoryName;

    @OneToMany(mappedBy = "consultationCategory")
    @JsonIgnore
    private List<Schedule> schedules;

    public ConsultationCategory(String categoryName, List<Schedule> schedules) {
        this.categoryName = categoryName;
        this.schedules = schedules;
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
}
