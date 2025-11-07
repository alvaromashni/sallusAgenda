package com.salus.agenda.Dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientRequestLoginDto(@Email(message = "Invalid email format")@NotBlank(message = "email field can not be blank")String emai, @NotBlank(message = "password field can not be blank!")String password) {
}
