package com.salus.agenda.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleSummaryDto(String patientName, String professionalName, String emailPatient,
        String emailProfessional, LocalDate consultationDate, LocalTime consultationTime,
        String consultationDescription) {

}
