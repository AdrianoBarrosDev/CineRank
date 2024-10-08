package com.at1.casa_cultural.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonBackReference
    private Filme filme;

    private String detalhes;
    private int nota;

    public Analise() {};
    
    public Analise(Long id, Filme filme, String detalhes, int nota) {
        this.id = id;
        this.filme = filme;
        this.detalhes = detalhes;
        this.nota = nota;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {    
        this.id = id;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
