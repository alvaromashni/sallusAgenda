package com.salus.agenda.Dtos;

import com.salus.agenda.Models.PersonalData;

import jakarta.validation.constraints.NotBlank;

public record ProfessionalRequestDto(
        @NotBlank(message = "Personal data field can not be null!") PersonalData personalData,
        @NotBlank(message = "The field crm can not be null!") String crm,
        @NotBlank(message = "Occupation field can not be null!") String occupation,
        @NotBlank(message = "Expertise field can not be null") String expertise) {

}
