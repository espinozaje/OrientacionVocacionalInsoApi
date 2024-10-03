package com.vocacional.orientacionvocacional.repository;

import com.vocacional.orientacionvocacional.model.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}

