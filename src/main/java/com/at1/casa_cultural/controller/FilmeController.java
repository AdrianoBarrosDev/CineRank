package com.at1.casa_cultural.controller;

import com.at1.casa_cultural.model.Analise;
import com.at1.casa_cultural.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
    private List<Filme> filmes = new ArrayList<>();
    private List<Analise> analises = new ArrayList<>();
    private Long filmeIdCounter = 1L;
    private Long analiseIdCounter = 1L;

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "listarFilmes"; // Nome do template
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("filme", new Filme(null, "", "", "", 0));
        return "cadastrarFilme"; // Nome do template
    }

    @PostMapping("/cadastrar")
    public String cadastrarFilme(@ModelAttribute Filme filme) {
        filme.setId(filmeIdCounter++);
        filmes.add(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("filme", filme);
        model.addAttribute("analises", analises);
        model.addAttribute("novaAnalise", new Analise(null, filme, "", 0));
        return "detalhesFilme"; // Nome do template
    }

    @PostMapping("/{id}/analisar")
    public String adicionarAnalise(@PathVariable Long id, @ModelAttribute Analise analise) {
        analise.setId(analiseIdCounter++);
        Filme filme = filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (filme != null) {
            analise.setFilme(filme);
            analises.add(analise);
        }
        return "redirect:/filmes/" + id;
    }
}
