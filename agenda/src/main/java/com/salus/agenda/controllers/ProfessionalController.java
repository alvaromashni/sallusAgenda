package com.salus.agenda.controllers;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.salus.agenda.dtos.request.HoursRequestDto;
import com.salus.agenda.dtos.response.ProfessionalDataResponseDto;
import com.salus.agenda.dtos.response.ProfessionalResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salus.agenda.dtos.request.ProfessionalRequestDto;
import com.salus.agenda.services.ProfessionalUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {
    private final ProfessionalUserService professionalUserService;

    public ProfessionalController(ProfessionalUserService professionalUserService) {
        this.professionalUserService = professionalUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerProfessional(@RequestBody @Valid ProfessionalRequestDto professionalDto) {
        professionalUserService.registerProfessionalUser(professionalDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfessionalData(@PathVariable UUID id,
            @RequestBody ProfessionalRequestDto professionalDto) {
        professionalUserService.uptadeProfessionalData(id, professionalDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<?> softDeleteProfessional(@PathVariable UUID id) {
        professionalUserService.softDeleteProfessional(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/updateHours/{id}")
    public ResponseEntity<?> updateHours(@PathVariable UUID id, @RequestBody HoursRequestDto dto){
        professionalUserService.updateProfessionalHours(id, dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/deleteHours/{id}")
    public ResponseEntity<?> deleteHours(@PathVariable UUID id, @RequestBody LocalTime hour){
        professionalUserService.deleteProfessionalHours(id, hour);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findAllHours/{id}")
    public ResponseEntity<?>findAllProfessionalHours(@PathVariable UUID id){
       return ResponseEntity.ok(professionalUserService.findAllProfessionalHours(id));

    }
    @GetMapping("/findAll")
    public ResponseEntity<List<ProfessionalResponseDto>> findAllProfessional(){
        return ResponseEntity.ok(professionalUserService.listAllProfessional());
    }
    @GetMapping("/findProfessionalData/{id}")
    public ResponseEntity<ProfessionalDataResponseDto>findProfessionalData(@PathVariable UUID id){
        return ResponseEntity.ok(professionalUserService.findProfessionalData(id));
    }

}
