package com.salus.agenda.Services.Auth;

import com.salus.agenda.Configuration.Security.JwtUtil;
import com.salus.agenda.Dtos.Request.ProfessionalLoginRequestDto;
import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Repositories.ProfessionalRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalUserAuthService implements UserDetailsService {
    private final ProfessionalRepository professionalRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ProfessionalUserAuthService(ProfessionalRepository professionalRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.professionalRepository = professionalRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ProfessionalUser professionalUser = professionalRepository.findByPersonalDataEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Professional not found"));
        return professionalUser;
    }
    public String login(ProfessionalLoginRequestDto dto){
        ProfessionalUser professionalUser = professionalRepository.findByPersonalDataEmail(dto.email())
                .orElseThrow(() -> new UsernameNotFoundException("Professional not found!"));
        System.out.println("senha do profissional: " + professionalUser.getPassword());
        if (!passwordEncoder.matches(dto.password(), professionalUser.getPassword())){
            throw new BadCredentialsException("Wrong password!");
        }
        return jwtUtil.generateToken(dto.email());

    }
    public ProfessionalUser loadByEmail(String email){
        ProfessionalUser professionalUser = professionalRepository.findByPersonalDataEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Professional not found"));
        return professionalUser;
    }
}
