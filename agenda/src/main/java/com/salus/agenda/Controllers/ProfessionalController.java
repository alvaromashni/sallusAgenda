package com.salus.agenda.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.salus.agenda.Dtos.ProfessionalRequestDto;
import com.salus.agenda.Dtos.ProfessionalResponseDto;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Services.ProfessionalUserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
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
        try {
            ProfessionalUser newProfessional = professionalUserService.registerProfessionalUser(professionalDto);
            ProfessionalResponseDto response = new ProfessionalResponseDto(
                    newProfessional.getIdProfessionalUser(),
                    newProfessional.getCrm(),
                    newProfessional.getPersonalData().getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProfessionalData(@PathVariable Long id,
            @RequestBody ProfessionalRequestDto professionalDto) {
        try {
            professionalUserService.uptadeProfessionalData(id, professionalDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
