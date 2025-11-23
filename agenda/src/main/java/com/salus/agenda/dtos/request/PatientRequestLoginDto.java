package com.salus.agenda.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientRequestLoginDto(@Email(message = "Invalid email format")@NotBlank(message = "email field can not be blank")String email, @NotBlank(message = "password field can not be blank!")String password) {
}
