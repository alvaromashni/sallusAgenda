package com.salus.agenda.Services.Auth;

import com.salus.agenda.Configuration.Security.JwtUtil;
import com.salus.agenda.Dtos.Request.AdminLoginRequestDto;
import com.salus.agenda.Models.Admin;
import com.salus.agenda.Repositories.AdminRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthService(AdminRepository adminRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Admin not found!"));
    }
    public String login(AdminLoginRequestDto dto){
        Admin admin = adminRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        if (!passwordEncoder.matches(dto.password(), admin.getPassword())){
            throw new BadCredentialsException("Wrong password!");
        }
        return jwtUtil.generateToken(dto.username());
    }
    public Admin loadByUsername(String username){
        return adminRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
    }

}
