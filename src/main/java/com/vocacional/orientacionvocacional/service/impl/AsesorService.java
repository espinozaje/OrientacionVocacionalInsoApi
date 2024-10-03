package com.vocacional.orientacionvocacional.service.impl;
import com.vocacional.orientacionvocacional.dto.AsesorDTO;
import com.vocacional.orientacionvocacional.model.entity.Asesor;
import com.vocacional.orientacionvocacional.model.enums.ERole;
import com.vocacional.orientacionvocacional.repository.AsesorRepository;
import com.vocacional.orientacionvocacional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsesorService {
    @Autowired
    private AsesorRepository asesorRepository;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Asesor> obtenerPerfilAsesorPorEmail(String email) {
        return asesorRepository.findByEmail(email);
    }

    public List<Asesor> listarTodosLosAsesores() {
        return asesorRepository.findAll();
    }


    public void registrarAsesor(AsesorDTO asesorDTO) {

        Asesor asesor = new Asesor();
        ERole eRole = ERole.ASESOR;

        asesor.setFirstName(asesorDTO.getFirstName());
        asesor.setLastName(asesorDTO.getLastName());
        asesor.setEmail(asesorDTO.getEmail());
        asesor.setEspecialidad(asesorDTO.getEspecialidad());
        asesor.setPassword(passwordEncoder.encode(asesorDTO.getPassword())); // Asegúrate de que estés utilizando un codificador de contraseñas
        asesor.setRole(eRole);
        usuarioRepository.save(asesor);
    }
}


