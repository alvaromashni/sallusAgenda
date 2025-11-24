package com.salus.agenda.controllers;

import com.salus.agenda.dtos.request.ConsultationLinkRequestDto;
import com.salus.agenda.dtos.response.ConsultationLinkResponseDto;
import com.salus.agenda.dtos.response.ValidateLinkResponseDto;
import com.salus.agenda.services.ConsultationLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consultationLink")
public class ConsultationLinkController {
    private final ConsultationLinkService consultationLinkService;

    public ConsultationLinkController(ConsultationLinkService consultationLinkService) {
        this.consultationLinkService = consultationLinkService;
    }
    @PostMapping("/generate")
    public ResponseEntity<ConsultationLinkResponseDto> generateLink(@RequestBody ConsultationLinkRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(consultationLinkService.generateLink(dto.professionalId()));
    }
    @GetMapping("/validate/{id}")
    public ResponseEntity<?> validateLink(@PathVariable UUID id){

        return ResponseEntity.ok(new ValidateLinkResponseDto(consultationLinkService.validateLink(id)));
    }
    @PatchMapping("/disable/{id}")
    public ResponseEntity<?> disableLink(@PathVariable UUID id){
        consultationLinkService.disableLink(id);
        return ResponseEntity.ok().build();
    }
}