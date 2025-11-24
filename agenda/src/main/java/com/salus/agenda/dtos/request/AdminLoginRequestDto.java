package com.salus.agenda.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AdminLoginRequestDto(@NotBlank(message = "Field username can not be null")String username, @NotBlank(message = "Field password can not be blank")String password) {
}
