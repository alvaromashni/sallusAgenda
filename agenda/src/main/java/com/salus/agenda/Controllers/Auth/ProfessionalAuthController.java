package com.salus.agenda.Controllers.Auth;

import com.salus.agenda.Dtos.Request.ProfessionalLoginRequestDto;
import com.salus.agenda.Dtos.Response.ProfessionalLoginResponseDto;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Services.Auth.ProfessionalUserAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/professional")
public class ProfessionalAuthController {
    private final ProfessionalUserAuthService professionalService;

    public ProfessionalAuthController(ProfessionalUserAuthService professionalService) {
        this.professionalService = professionalService;
    }
    @PostMapping("/login")
    public ResponseEntity<ProfessionalLoginResponseDto> login(@Valid@RequestBody ProfessionalLoginRequestDto dto){
        String token = professionalService.login(dto);
        ProfessionalUser professionalUser = professionalService.loadByEmail(dto.email());
        return ResponseEntity.ok(new ProfessionalLoginResponseDto(professionalUser.getIdProfessionalUser(), token));
    }
}
