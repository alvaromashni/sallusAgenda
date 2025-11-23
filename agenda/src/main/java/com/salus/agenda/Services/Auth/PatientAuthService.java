package com.salus.agenda.Services.Auth;

import com.salus.agenda.Configuration.Security.JwtUtil;
import com.salus.agenda.Dtos.Request.PatientRequestLoginDto;
import com.salus.agenda.Models.Patient;
import com.salus.agenda.Repositories.PatientRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PatientAuthService implements UserDetailsService {
    private final PatientRepository patientRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    public PatientAuthService(PatientRepository patientRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.patientRepository = patientRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return patientRepository.findByPersonalDataEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
    }
    public String login(PatientRequestLoginDto dto){
        Patient patient = patientRepository.findByPersonalDataEmail(dto.email())
                .orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
        if (!passwordEncoder.matches(dto.password(), patient.getPersonalData().getPassword())){
            throw new BadCredentialsException("Wrong password!");
        }
        return jwtUtil.generateToken(dto.email());
    }
    public Patient loadByEmail(String email){
        return patientRepository.findByPersonalDataEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
    }
}
