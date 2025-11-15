package com.salus.agenda.Models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class ProfessionalUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProfessionalUser;
    @Embedded
    private PersonalData personalData;
    @NotBlank(message = "Field crm can not be blank")
    @Column(unique = true, nullable = false)
    private String crm;
    @NotBlank(message = "Field occupation can not be blank")
    @Column(nullable = false)
    private String occupation;
    @NotBlank(message = "Field expertise can not be blank")
    @Column(nullable = false)
    private String expertise;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean active = true;
    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;
    private String role = "USER";
    @OneToMany(mappedBy = "professionalUser")
    @JsonIgnore
    private List<Schedule> schedules;
    @Embedded
    private Hours hours;

    public ProfessionalUser(PersonalData personalData, String crm, String occupation, String expertise,
            List<Schedule> schedules, UUID idProfessionalUser, LocalDateTime createdAt, boolean active,
            boolean isDeleted, LocalDateTime deletedAt, String role ) {
        this.personalData = personalData;
        this.crm = crm;
        this.occupation = occupation;
        this.expertise = expertise;
        this.schedules = schedules;
        this.idProfessionalUser = idProfessionalUser;
        this.createdAt = createdAt;
        this.active = active;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
        this.role = "USER";
    }

    public ProfessionalUser() {

    }

    public UUID getIdProfessionalUser() {
        return idProfessionalUser;
    }

    public void setIdProfessionalUser(UUID idProfessionalUser) {
        this.idProfessionalUser = idProfessionalUser;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRole()));
    }

    @Override
    public String getPassword() {
        return personalData.getPassword();
    }

    @Override
    public String getUsername() {
        return personalData.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
