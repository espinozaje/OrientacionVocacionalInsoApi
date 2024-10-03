package com.vocacional.orientacionvocacional.repository;


import com.vocacional.orientacionvocacional.model.entity.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsesorRepository extends JpaRepository<Asesor, Long> {
    // Buscar un asesor por email
    Optional<Asesor> findByEmail(String email);
}