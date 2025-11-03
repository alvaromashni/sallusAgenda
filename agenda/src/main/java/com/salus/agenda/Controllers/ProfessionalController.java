package com.salus.agenda.Controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Dtos.Request.ProfessionalRequestDto;
import com.salus.agenda.Services.ProfessionalUserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @DeleteMapping("hard-delete/{id}")
    public ResponseEntity<?> hardDeleteProfessional(@PathVariable UUID id) {
        professionalUserService.hardDeleteProfessional(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("soft-delete")
    public ResponseEntity<?> softDeleteProfessional(@PathVariable UUID id) {
        professionalUserService.softDeleteProfessional(id);
        return ResponseEntity.ok().build();
    }

}
