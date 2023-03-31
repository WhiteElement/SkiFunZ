package com.manu.BergfexScraper.repository;

import com.manu.BergfexScraper.model.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URL;

public interface SkiResortRepository extends JpaRepository<SkiResort, Long> {

    boolean existsByName(String name);

    SkiResort findByUrl(URL url);
}
