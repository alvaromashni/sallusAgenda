package com.salus.agenda.Controllers;

import com.salus.agenda.Dtos.Request.ConsultationLinkRequestDto;
import com.salus.agenda.Dtos.Response.ConsultationLinkResponseDto;
import com.salus.agenda.Services.ConsultationLinkService;
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
        consultationLinkService.validateLink(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/disable/{id}")
    public ResponseEntity<?> disableLink(@PathVariable UUID id){
        consultationLinkService.disableLink(id);
        return ResponseEntity.ok().build();
    }
}
