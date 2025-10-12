package com.salus.agenda.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleDto(LocalDate consultationDate, LocalTime consultationTime, String consultationDescription,
        Long consultationCategoryId, Long patientId, Long professionalUserId) {

}
