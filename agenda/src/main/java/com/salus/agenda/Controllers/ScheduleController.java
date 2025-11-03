package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Dtos.Request.ScheduleRequestDto;
import com.salus.agenda.Services.ScheduleService;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        scheduleService.hardDeleteSchedule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("delete/softDelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {
        scheduleService.softDeleteSchedule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isScheduled/{id}")
    public ResponseEntity<?> isScheduled(@RequestParam LocalDate day, @PathVariable UUID professionalUserId) {

        return ResponseEntity.ok(scheduleService.isScheduledForTheDay(professionalUserId, day));
    }

    @GetMapping("/findSchedule/{id}")
    public ResponseEntity<?> findAllScheduledForTheDay(@PathVariable UUID professionalId,
            @RequestParam LocalDate date) {
        return ResponseEntity.ok(scheduleService.getSchedulesForTheDay(professionalId, date));
    }

}
