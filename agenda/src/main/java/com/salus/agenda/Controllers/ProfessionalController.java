package com.salus.agenda.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salus.agenda.Models.ProfessionalUser;
import com.salus.agenda.Repositories.ProfessionalRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cadastro")
public class ProfessionalController {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalController(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @PostMapping("/professional")
    public void postMethodName(@RequestBody ProfessionalUser professionalUser) {
        professionalRepository.save(professionalUser);

    }

}
