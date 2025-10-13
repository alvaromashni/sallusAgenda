package com.salus.agenda.Dtos;

import com.salus.agenda.Models.PersonalData;

import jakarta.validation.Valid;

public record PatientRequestDto(@Valid PersonalData personalData) {

}
