package com.salus.agenda.Models;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPatient;
    @Embedded
    private PersonalData personalData;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Schedule> schedules;

    public Patient() {
    }

    public Patient(PersonalData personalData, List<Schedule> schedules) {
        this.personalData = personalData;
        this.schedules = schedules;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
