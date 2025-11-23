package com.salus.agenda.configuration;


import com.salus.agenda.models.Admin;
import com.salus.agenda.repositories.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder){
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;
    @PostConstruct
    public void createDefaultAdmin(){
        boolean adminExists = adminRepository.findByUsername(adminUsername).isPresent();
        if (!adminExists){
            Admin admin = new Admin();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");
            adminRepository.save(admin);
        }
    }
}
