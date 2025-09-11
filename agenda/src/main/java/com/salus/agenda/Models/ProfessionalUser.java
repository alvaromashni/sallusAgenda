package com.salus.agenda.Models;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public ProfessionalUser(PersonalData personalData, String crm) {
        this.personalData = personalData;
        this.crm = crm;
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
}
