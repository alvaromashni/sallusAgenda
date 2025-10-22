package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.salus.agenda.Dtos.ScheduleRequestDto;
import com.salus.agenda.Repositories.ProfessionalRepository;
import com.salus.agenda.Services.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ProfessionalRepository professionalRepository;

    public ScheduleController(ScheduleService scheduleService, ProfessionalRepository professionalRepository) {
        this.scheduleService = scheduleService;
        this.professionalRepository = professionalRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleRequestDto schedule) {
        scheduleService.registerSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    



}
