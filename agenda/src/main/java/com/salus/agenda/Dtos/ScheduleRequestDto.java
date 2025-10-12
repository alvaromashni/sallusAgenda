package com.salus.agenda.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScheduleRequestDto(
        @NotNull(message = "The consultation date can not be null!") LocalDate consultationDate,
        @NotNull(message = "The consultation time can not be null!") LocalTime consultationTime,
        @NotBlank(message = "The consultation description can not be null!") String consultationDescription,
        @NotBlank(message = "The consultation category id can not be null!") Long consultationCategoryId,
        @NotBlank(message = "The patient id can not be null!") Long patientId,
        @NotBlank(message = "The professional user id can not be null!") Long professionalUserId) {

}
