package com.at1.casa_cultural.controller;

import com.at1.casa_cultural.model.Filme;
import com.at1.casa_cultural.repository.AnaliseRepository;
import com.at1.casa_cultural.repository.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }
    
    @GetMapping("/filmes")
    public String listarFilmes(Model model) {
        return "listarFilmes";
    }

    @PostMapping
    public Filme adicionarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(filmeAtualizado.getTitulo());
                    filme.setSinopse(filmeAtualizado.getSinopse());
                    filme.setGenero(filmeAtualizado.getGenero());
                    filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
                    Filme atualizado = filmeRepository.save(filme);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFilme(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeRepository.delete(filme);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.status(404).build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeComAnalises(@PathVariable Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            return ResponseEntity.ok(filme.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
