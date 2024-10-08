package com.at1.casa_cultural.repository;

import com.at1.casa_cultural.model.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliseRepository extends JpaRepository<Analise, Long> {
    List<Analise> findByFilmeId(Long filmeId);
}
