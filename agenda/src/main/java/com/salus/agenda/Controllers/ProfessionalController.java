package com.salus.agenda.Controllers;

import java.util.UUID;

import com.salus.agenda.Dtos.Request.HoursDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.salus.agenda.Dtos.Request.ProfessionalRequestDto;
import com.salus.agenda.Services.ProfessionalUserService;

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

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProfessionalData(@PathVariable UUID id,
            @RequestBody ProfessionalRequestDto professionalDto) {
        professionalUserService.uptadeProfessionalData(id, professionalDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("soft-delete/{id}")
    public ResponseEntity<?> softDeleteProfessional(@PathVariable UUID id) {
        professionalUserService.softDeleteProfessional(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/updateHours")
    public ResponseEntity<?> updateHours(@PathVariable UUID id, @RequestBody HoursDto dto){
        professionalUserService.updateProfessionalHours(id, dto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}/deleteHours")
    public ResponseEntity<?> deleteHours(@PathVariable UUID id, @RequestBody HoursDto dto){
        professionalUserService.deleteProfessionalHours(id, dto);
        return ResponseEntity.ok().build();
    }

}
