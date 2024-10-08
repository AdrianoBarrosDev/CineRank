package com.at1.casa_cultural.controller;

import com.at1.casa_cultural.model.Analise;
import com.at1.casa_cultural.model.Filme;
import com.at1.casa_cultural.repository.AnaliseRepository;
import com.at1.casa_cultural.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;
    
    @PostMapping
    public ResponseEntity<Analise> criarAnalise(@RequestBody Analise novaAnalise) {
        
        Filme filme = filmeRepository.findById(novaAnalise.getFilme().getId()).orElse(null);
        if (filme == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        novaAnalise.setFilme(filme);
        Analise analiseSalva = analiseRepository.save(novaAnalise);

        return ResponseEntity.status(HttpStatus.CREATED).body(analiseSalva);
    }
    
}
