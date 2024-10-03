package com.vocacional.orientacionvocacional.Controller;

import com.vocacional.orientacionvocacional.model.entity.Asesor;
import com.vocacional.orientacionvocacional.service.impl.AsesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asesores")
public class AsesorController {

    @Autowired
    private AsesorService asesorService;




    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfilAsesor() {
        // Obtener la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extraer el email del usuario autenticado (supuesto en el token JWT)
        String email = authentication.getName();

        // Buscar el perfil del asesor en la base de datos por su email
        Optional<Asesor> asesor = asesorService.obtenerPerfilAsesorPorEmail(email);

        // Si el perfil del asesor existe, devolverlo; de lo contrario, devolver un error
        if (asesor.isPresent()) {
            return ResponseEntity.ok(asesor.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"Perfil del asesor no encontrado.\"}");
        }
    }

    @RequestMapping(value = "/perfil", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listarasesores")
    public ResponseEntity<List<Asesor>> listarAsesores() {
        List<Asesor> asesores = asesorService.listarTodosLosAsesores();
        return ResponseEntity.ok(asesores);
    }

}
