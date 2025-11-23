package com.salus.agenda.dtos.request;

import java.time.LocalTime;
import java.util.List;

public record HoursRequestDto(List<LocalTime> hours) {

}
