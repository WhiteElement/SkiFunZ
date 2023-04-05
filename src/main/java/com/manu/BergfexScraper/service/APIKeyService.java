package com.manu.BergfexScraper.service;

import com.manu.BergfexScraper.model.APIKey;
import com.manu.BergfexScraper.repository.APIKeyRepository;

import org.springframework.stereotype.Service;

@Service
public class APIKeyService {

    private final APIKeyRepository apiKeyRepository;

    public APIKeyService(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

//    public APIKeyService(APIKeyRepository apiKeyRepository) {
//        this.apiKeyRepository = apiKeyRepository;
//    }

    public APIKey createNew(String username) {
        APIKey apiKey = new APIKey();
        apiKey.setUsername(username);
        return apiKeyRepository.save(apiKey);
    }

    public boolean isValidKey(String apiKey) {
        return apiKeyRepository.existsByKeyValue(apiKey);
    }
}
