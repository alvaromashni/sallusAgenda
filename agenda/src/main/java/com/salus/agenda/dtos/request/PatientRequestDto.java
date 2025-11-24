package com.salus.agenda.dtos.request;

import com.salus.agenda.models.PersonalData;

import jakarta.validation.Valid;

public record PatientRequestDto(@Valid PersonalData personalData) {

}
