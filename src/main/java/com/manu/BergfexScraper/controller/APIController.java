package com.manu.BergfexScraper.controller;

import com.manu.BergfexScraper.dto.SingleResortResponseDTO;
import com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO;
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
        return skiResortService.findAllResorts(sorted);
    }

    @GetMapping("/{id}")
    public SingleResortResponseDTO showResortWithTimeline(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean current) {
        return skiResortService.findSingleResort(id, current);
    }
}
