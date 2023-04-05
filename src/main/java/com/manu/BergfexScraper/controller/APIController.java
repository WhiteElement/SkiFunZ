package com.manu.BergfexScraper.controller;

import com.manu.BergfexScraper.dto.SingleResortResponseDTO;
import com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO;
import com.manu.BergfexScraper.service.APIKeyService;
import com.manu.BergfexScraper.service.SkiResortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resorts")
public class APIController {

    private final SkiResortService skiResortService;
    private final APIKeyService apiKeyService;

    public APIController(SkiResortService skiResortService, APIKeyService apiKeyService) {
        this.skiResortService = skiResortService;
        this.apiKeyService = apiKeyService;
    }

    @GetMapping()
    public ResponseEntity showAllResorts(@RequestHeader(name = "api-access-key", required = false) String apiKey,
                                                                       @RequestParam(defaultValue = "false") boolean sorted,
                                                                       @RequestParam(defaultValue = "0") Integer minSnowHeightMountain,
                                                                       @RequestParam(defaultValue = "0") Integer minSnowHeightValley) {
        if(apiKey == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("kein API Key");
        }
        if(apiKeyService.isValidKey(apiKey)) {
            return new ResponseEntity<>(skiResortService.findAllResorts(sorted, minSnowHeightMountain, minSnowHeightValley), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ungültiger API Key");
    }

    @GetMapping("/{id}")
    public SingleResortResponseDTO showResortWithTimeline(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean current) {
        return skiResortService.findSingleResort(id, current);
    }
}
