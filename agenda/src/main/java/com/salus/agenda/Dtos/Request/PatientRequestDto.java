package com.salus.agenda.Dtos.Request;

import com.salus.agenda.Models.PersonalData;

import jakarta.validation.Valid;

public record PatientRequestDto(@Valid PersonalData personalData) {

}
