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
public class ProfessionalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdProfessionalUser;
    @Embedded
    private PersonalData personalData;
    private String crm;
    private String occupation;
    private String expertise;
    @OneToMany(mappedBy = "professionalUser")
    @JsonIgnore
    private List<Schedule> schedules;

    public ProfessionalUser(PersonalData personalData, String crm, String occupation, String expertise,
            List<Schedule> schedules) {
        this.personalData = personalData;
        this.crm = crm;
        this.occupation = occupation;
        this.expertise = expertise;
        this.schedules = schedules;
    }

    public ProfessionalUser() {

    }

    public long getIdProfessionalUser() {
        return IdProfessionalUser;
    }

    public void setIdProfessionalUser(long idProfessionalUser) {
        IdProfessionalUser = idProfessionalUser;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
