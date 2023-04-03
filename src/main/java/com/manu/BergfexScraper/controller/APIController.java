package com.manu.BergfexScraper.controller;

import com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO;
import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.service.SkiResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resorts")
public class APIController {

    @Autowired
    SkiResortService skiResortService;

    @GetMapping()
    public List<SkiResortAndTimelineDTO> showAllResorts(@RequestParam(defaultValue = "false") boolean sorted) {
        return skiResortService.findAll(sorted);
    }

    @GetMapping("/{id}")
    public SkiResort showResortWithTimeline(@PathVariable Long id) {
        return skiResortService.findById(id);
    }
}
