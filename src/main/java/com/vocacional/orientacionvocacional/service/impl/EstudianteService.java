package com.vocacional.orientacionvocacional.service.impl;

import com.vocacional.orientacionvocacional.dto.EstudianteDTO;
import com.vocacional.orientacionvocacional.model.entity.Estudiante;
import com.vocacional.orientacionvocacional.model.enums.ERole;
import com.vocacional.orientacionvocacional.repository.EstudianteRepository;
import com.vocacional.orientacionvocacional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public void registrarEstudiante(EstudianteDTO estudianteDTO) {

        Estudiante estudiante = new Estudiante();
        ERole eRole = ERole.ESTUDIANTE;

        // Asignar atributos
        estudiante.setFirstName(estudianteDTO.getFirstName());
        estudiante.setLastName(estudianteDTO.getLastName());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setPassword(passwordEncoder.encode(estudianteDTO.getPassword())); // Asegúrate de que estés utilizando un codificador de contraseñas
        estudiante.setRole(eRole);
        usuarioRepository.save(estudiante);
    }
}

