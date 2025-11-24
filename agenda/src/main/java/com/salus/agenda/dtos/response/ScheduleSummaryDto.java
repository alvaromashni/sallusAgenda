package com.salus.agenda.dtos.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

public interface ScheduleSummaryDto {
        Long getIdSchedule();

        String getPatientName();

        String getProfessionalName();

        String getPatientEmail();

        String getProfessionalUserEmail();

        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate getConsultationDate();

        @JsonSerialize(using = LocalTimeSerializer.class)
        @JsonFormat(pattern = "HH:mm")
        LocalTime getConsultationTime();

        String getConsultationDescription();

}
