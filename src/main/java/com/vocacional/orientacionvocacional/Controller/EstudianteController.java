package com.vocacional.orientacionvocacional.Controller;

import com.vocacional.orientacionvocacional.model.entity.Estudiante;
import com.vocacional.orientacionvocacional.service.impl.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;
    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfilEstudiante() {
        // Lógica para obtener el perfil del estudiante
        return ResponseEntity.ok().body("{\"message\": \"Perfil del estudiante.\"}");
    }

    @GetMapping("/listarestudiantes")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }
}
