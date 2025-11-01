package com.salus.agenda.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPatient;
    @Embedded
    private PersonalData personalData;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Schedule> schedules;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean active = true;
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;

    public Patient() {
    }

    public Patient(PersonalData personalData, List<Schedule> schedules, boolean active, LocalDateTime createdAt,
            UUID idPatient, boolean isDeleted, LocalDateTime deletedAt) {
        this.personalData = personalData;
        this.schedules = schedules;
        this.active = active;
        this.createdAt = createdAt;
        this.idPatient = idPatient;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public UUID getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(UUID idPatient) {
        this.idPatient = idPatient;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
