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

    public Patient() {
    }

    public Patient(PersonalData personalData, List<Schedule> schedules, boolean active, LocalDateTime createdAt) {
        this.personalData = personalData;
        this.schedules = schedules;
        this.active = active;
        this.createdAt = createdAt;
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
    
}
