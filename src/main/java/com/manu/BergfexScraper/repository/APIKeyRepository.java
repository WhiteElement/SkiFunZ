package com.manu.BergfexScraper.repository;

import com.manu.BergfexScraper.model.APIKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIKeyRepository extends JpaRepository<APIKey, Long> {
    boolean existsByKeyValue(String apiKey);

    boolean existsByUsername(String username);
}
