package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Dtos.ScheduleRequestDto;
import com.salus.agenda.Services.ScheduleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleRequestDto schedule) {
        scheduleService.registerSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
