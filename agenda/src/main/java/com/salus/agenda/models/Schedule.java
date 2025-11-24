package com.salus.agenda.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSchedule;
    @Column(nullable = false)
    @NotNull(message = "Consultation date can not be null")
    private LocalDate consultationDate;
    @NotNull(message = "Consultation time can not be null")
    private LocalTime consultationTime;

    private String consultationDescription;
    @ManyToOne
    @JoinColumn(name = "consultationCategory_id")
    private ConsultationCategory consultationCategory;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "professionalUser_id")
    private ProfessionalUser professionalUser;
    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;

    public Schedule() {
    }

    public Schedule(LocalDate consultationDate, String consultationDescription,
            ConsultationCategory consultationCategory, Patient patient, ProfessionalUser professionalUser,
            LocalTime consultationTime, boolean isDeleted, LocalDateTime deletedAt) {
        this.consultationDate = consultationDate;
        this.consultationDescription = consultationDescription;
        this.consultationCategory = consultationCategory;
        this.patient = patient;
        this.professionalUser = professionalUser;
        this.consultationTime = consultationTime;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
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

    public LocalTime getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(LocalTime consultationTime) {
        this.consultationTime = consultationTime;
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
