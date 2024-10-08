package com.at1.casa_cultural.repository;

import com.at1.casa_cultural.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
        
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
}
        