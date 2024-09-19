package com.vocacional.orientacionvocacional.Controller;

import com.vocacional.orientacionvocacional.model.entity.Pregunta;
import com.vocacional.orientacionvocacional.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController
@RequestMapping("/vocational-test")
@CrossOrigin(origins = "http://localhost:4200")
public class VocationalController {
    @Autowired
    private PreguntaRepository preguntaRepository;


    @GetMapping("/questions")
    public List<Pregunta> getQuestions(){
        List<Pregunta> preguntas = preguntaRepository.findAll();
        System.out.println("Preguntas enviadas: " + preguntas);
        return preguntas;
    }

}

