package com.salus.agenda.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Embeddable
public class PersonalData {
    @Column( nullable = false)
    @NotBlank(message = "Field name can not be blank")
    private String name;
    @Column(unique = true, nullable = false)
    @CPF(message = "Invalid cpf format!")
    private String cpf;
    @Email(message = "Invalid email format!")
    @NotBlank(message = "Email field can not be blank!")
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate birthDate;
    private String gender;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Field phoneNumber can not be blank")
    private String phoneNumber;
    @Column(nullable = false)
    @NotBlank(message = "Field password can not be blank")
    @Size(min = 8, message = "Password must have at least 8 digits")
    private String password;

    public PersonalData(String name, String cpf, String email, LocalDate birthDate, String gender,
            String phoneNumber) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public PersonalData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
