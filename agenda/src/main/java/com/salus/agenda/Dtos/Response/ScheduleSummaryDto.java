package com.salus.agenda.Dtos.Response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

public interface ScheduleSummaryDto {
        String getPatientName();

        String getProfessionalName();

        String getPatientEmail();

        String getProfessionalUserEmail();

        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate getConsultationDate();

        @JsonSerialize(using = LocalTimeSerializer.class)
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime getConsultationTime();

        String getConsultationDescription();

}
