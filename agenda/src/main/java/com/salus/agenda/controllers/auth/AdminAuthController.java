package com.salus.agenda.controllers.auth;

import com.salus.agenda.dtos.request.AdminLoginRequestDto;
import com.salus.agenda.dtos.response.AdminLoginResponse;
import com.salus.agenda.services.auth.AdminAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController {
    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginRequestDto dto){
        return ResponseEntity.ok(new AdminLoginResponse(adminAuthService.login(dto)));
    }
}
