package com.at1.casa_cultural.controller;

import com.at1.casa_cultural.model.Analise;
import com.at1.casa_cultural.model.Filme;
<<<<<<< HEAD
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
=======
import com.at1.casa_cultural.repository.AnaliseRepository;
import com.at1.casa_cultural.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeController {
    
    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseRepository analiseRepository;

    @GetMapping("/filmes")
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "listarFilmes";
    }
    
    @GetMapping("/filmes/cadastrar")
    public String cadastrarFilme(Model model) {
        List<Filme> filmes = filmeRepository.findAll();
        model.addAttribute("filmes", filmes);
        return "cadastrarFilme";
    }
    
    @GetMapping("/detalhesfilme/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme == null) {
            return "redirect:/filmes";
        }
        
        List<Analise> analises = analiseRepository.findByFilmeId(id);

        model.addAttribute("filme", filme);
        model.addAttribute("analises", analises);
        model.addAttribute("novaAnalise", new Analise(null, filme, "", 0));
        return "detalhesFilme";
    }

    @PostMapping("/detalhesfilme/{id}/analisar")
    public String adicionarAnalise(@PathVariable Long id, @ModelAttribute Analise novaAnalise) {
        System.out.println("Nova Análise: " + novaAnalise.getDetalhes());

        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme != null) {
            novaAnalise.setFilme(filme);
            analiseRepository.save(novaAnalise);
        }

        return "redirect:/detalhesfilme/" + id;
    }
    
    @GetMapping("/filmes/editar/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme == null) {
            return "redirect:/filmes";
        }

        model.addAttribute("filme", filme);
        return "editarFilme";
    }
    
    @PostMapping("/filmes/editar/{id}")
    public String atualizarFilme(@PathVariable Long id, @ModelAttribute Filme filmeAtualizado) {
        Filme filmeExistente = filmeRepository.findById(id).orElse(null);
        if (filmeExistente != null) {
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
            filmeExistente.setSinopse(filmeAtualizado.getSinopse());
            filmeExistente.setGenero(filmeAtualizado.getGenero());
            filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            filmeRepository.save(filmeExistente);
        }
        return "redirect:/filmes";
    }
    
>>>>>>> master
}
