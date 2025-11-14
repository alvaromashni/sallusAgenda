package com.salus.agenda.Dtos.Request;

import com.salus.agenda.Models.Hours;

import java.time.LocalTime;
import java.util.List;

public record HoursDto(List<LocalTime> hours) {

}
