package com.manu.BergfexScraper.service;

import com.manu.BergfexScraper.errorhandling.InvalidApiKeyException;
import com.manu.BergfexScraper.model.APIKey;
import com.manu.BergfexScraper.repository.APIKeyRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class APIKeyService {

    private final APIKeyRepository apiKeyRepository;

    public APIKeyService(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public APIKey createNew(String username) {
        APIKey apiKey = new APIKey();
        apiKey.setUsername(username);
        return apiKeyRepository.save(apiKey);
    }

    public boolean isInDatabase(String apiKey) {
        return apiKeyRepository.existsByKeyValue(apiKey);
    }

    public int countKeys() {
        return (int) apiKeyRepository.count();
    }

    public void validateAPIKey(String apiKey) throws InvalidApiKeyException {
        if(apiKey == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API-Access-Key darf nicht null sein");
        }
        if(!isInDatabase(apiKey)) {
            throw new InvalidApiKeyException("kein gültiger API-Access-Key");
        }
    }

    public boolean userNameAlreadyExists(String username) {
        return apiKeyRepository.existsByUsername(username);
    }
}
