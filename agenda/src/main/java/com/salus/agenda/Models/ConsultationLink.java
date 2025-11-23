package com.salus.agenda.Models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class ConsultationLink {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConsultationLink;
    private String url;
    private LocalDateTime expirationTime;
    private boolean isActive = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private ProfessionalUser professionalUser;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public ConsultationLink(UUID idConsultationLink, String url, boolean isActive, LocalDateTime expirationTime, LocalDateTime createdAt, boolean isDeleted, LocalDateTime deletedAt) {
        this.idConsultationLink = idConsultationLink;
        this.url = url;
        this.isActive = true;
        this.expirationTime = expirationTime;
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
        this.deletedAt = deletedAt;
    }
    public ConsultationLink(){}

    public UUID getIdConsultationLink() {
        return idConsultationLink;
    }

    public void setIdConsultationLink(UUID idConsultationLink) {
        this.idConsultationLink = idConsultationLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
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
}

