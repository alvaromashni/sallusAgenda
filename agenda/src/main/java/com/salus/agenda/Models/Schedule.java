package com.salus.agenda.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSchedule;
    private LocalDate consultationDate;
    private String consultationDescription;
    @ManyToOne
    private ConsultationCategory consultationCategory;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private ProfessionalUser professionalUser;

    public Schedule() {
    }

    public Schedule(LocalDate consultationDate, String consultationDescription,
            ConsultationCategory consultationCategory, Patient patient, ProfessionalUser professionalUser) {
        this.consultationDate = consultationDate;
        this.consultationDescription = consultationDescription;
        this.consultationCategory = consultationCategory;
        this.patient = patient;
        this.professionalUser = professionalUser;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationDescription() {
        return consultationDescription;
    }

    public void setConsultationDescription(String consultationDescription) {
        this.consultationDescription = consultationDescription;
    }

    public ConsultationCategory getConsultationCategory() {
        return consultationCategory;
    }

    public void setConsultationCategory(ConsultationCategory consultationCategory) {
        this.consultationCategory = consultationCategory;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ProfessionalUser getProfessionalUser() {
        return professionalUser;
    }

    public void setProfessionalUser(ProfessionalUser professionalUser) {
        this.professionalUser = professionalUser;
    }

    public long getIdSchedule() {
        return idSchedule;
    }
}
