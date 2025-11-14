package com.salus.agenda.Configuration.Security;

import com.salus.agenda.Services.Auth.AdminAuthService;
import com.salus.agenda.Services.Auth.PatientAuthService;
import com.salus.agenda.Services.Auth.ProfessionalUserAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final PatientAuthService patientAuthService;
    private final AdminAuthService adminAuthService;
    private final ProfessionalUserAuthService professionalUserAuthService;

    public JwtAuthFilter(JwtUtil jwtUtil, PatientAuthService patientAuthService, AdminAuthService adminAuthService, ProfessionalUserAuthService professionalUserAuthService) {
        this.jwtUtil = jwtUtil;
        this.patientAuthService = patientAuthService;
        this.adminAuthService = adminAuthService;
        this.professionalUserAuthService = professionalUserAuthService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer")){
                filterChain.doFilter(request, response);
                return;
            }
            String token = authHeader.substring(7);

            String email = JwtUtil.extractUsername(token);
            if (email == null || email.isBlank() || !JwtUtil.validateToken(token)){
                filterChain.doFilter(request, response);
                return;
            }
            if (!email.isBlank()){
                UserDetails user;
                if (uri.startsWith("/patient/")){
                    user = patientAuthService.loadUserByUsername(email);
                }else if (uri.startsWith("/admin/")){
                    user = adminAuthService.loadUserByUsername(email);
                }else if (uri.startsWith("/professional/")){
                    user = professionalUserAuthService.loadUserByUsername(email);
                } else{
                    throw new RuntimeException("User not identified!");
                }
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        filterChain.doFilter(request, response);

    }
}
