package com.salus.agenda.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessionalLoginRequestDto(@NotBlank(message ="Field email can not be blank")@Email(message = "Email format invalid")String email, @NotBlank(message = "Field password can not be blank")String password) {
}
