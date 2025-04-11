package com.manu.BergfexScraper.controller;

import com.manu.BergfexScraper.errorhandling.InvalidApiKeyException;
import com.manu.BergfexScraper.service.SkiResortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/resorts")
public class APIController {

    private final SkiResortService skiResortService;

    public APIController(SkiResortService skiResortService) {
        this.skiResortService = skiResortService;
    }

    @GetMapping()
    public ResponseEntity<?> showAllResorts(@RequestHeader(name = "api-access-key", required = false) String apiKey,
                                         @RequestParam(defaultValue = "false") boolean sorted,
                                         @RequestParam(defaultValue = "0") Integer minSnowHeightMountain,
                                         @RequestParam(defaultValue = "0") Integer minSnowHeightValley)
            throws InvalidApiKeyException, ResponseStatusException {

        return new ResponseEntity<>(skiResortService.findAllResorts(apiKey, sorted, minSnowHeightMountain, minSnowHeightValley), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showResortWithTimeline(@RequestHeader(name = "api-access-key", required = false) String apiKey,
                                                          @PathVariable Long id,
                                                          @RequestParam(defaultValue = "false") boolean current) throws InvalidApiKeyException {
        return new ResponseEntity<>(skiResortService.findSingleResort(apiKey, id, current), HttpStatus.OK);
    }
}
