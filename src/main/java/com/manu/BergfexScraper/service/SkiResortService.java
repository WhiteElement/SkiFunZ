package com.manu.BergfexScraper.service;

import com.manu.BergfexScraper.auxillary.SnowDataEntry;
import com.manu.BergfexScraper.dto.SingleResortResponseDTO;
import com.manu.BergfexScraper.dto.SkiResortAndAllTimelinesDTO;
import com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO;
import com.manu.BergfexScraper.errorhandling.InvalidApiKeyException;
import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.model.SkiResortTimeline;
import com.manu.BergfexScraper.repository.SkiResortRepository;
import com.manu.BergfexScraper.repository.SkiResortTimelineRepository;
import com.manu.BergfexScraper.scraper.BergfexWebScraper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SkiResortService {

    private BergfexWebScraper scraper;
    private final APIKeyService apiKeyService;
    private final SkiResortRepository skiResortRepository;
    private final SkiResortTimelineRepository skiResortTimelineRepository;

    SkiResortService(APIKeyService apiKeyService, SkiResortRepository skiResortRepository,
                     SkiResortTimelineRepository skiResortTimelineRepository) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortTimelineRepository = skiResortTimelineRepository;
        this.apiKeyService = apiKeyService;
        this.scraper = new BergfexWebScraper();
    }

    public String initNewResort(String urlstring) {
        try {
            scraper.setUrl(new URL(urlstring));
        } catch (MalformedURLException e) {
            return String.format("'%s' ist keine gültige URL", urlstring);
        }

        SkiResort skiResort = scraper.init();

        if (alreadyExists(skiResort))
            return String.format("'%s' ist bereits in der Datenbank", skiResort.getName());

        save(skiResort);
        updateSnowTimeline(skiResort);

        return "gespeichert";
    }

    private boolean alreadyExists(SkiResort skiResort) {
        return skiResortRepository.existsByName(skiResort.getName());
    }

    public void save(SkiResort skiResort) {
        skiResortRepository.save(skiResort);
    }

    public SkiResort findByUrl(URL url) {
        return skiResortRepository.findByUrl(url);
    }

    public void updateSnowTimeline(SkiResort skiResort) {
        scraper.setUrl(skiResort.getUrl());
        SkiResortTimeline timeline = scraper.updateSnowData(skiResort);
        skiResortTimelineRepository.save(timeline);
    }

    public BergfexWebScraper getScraper() {
        return scraper;
    }

    public void setScraper(BergfexWebScraper scraper) {
        this.scraper = scraper;
    }

    public List<SkiResortAndTimelineDTO> findAllResorts(String apiAccessKey, boolean sorted, Integer minSnowHeightMountain, Integer minSnowHeightValley) throws InvalidApiKeyException, ResponseStatusException{
        apiKeyService.validateAPIKey(apiAccessKey);
        if(sorted) {
            List<SkiResortAndTimelineDTO> skiResortAndTimelineDTOs = skiResortRepository.findAllResortsWithTimelineOrderedBySnowMountainHeight();
            return filterDTOListBy(skiResortAndTimelineDTOs, minSnowHeightValley, minSnowHeightMountain);
        } else {
            return skiResortRepository.findAllResortsWithTimeline();
        }
    }

    private List<SkiResortAndTimelineDTO> filterDTOListBy(List<SkiResortAndTimelineDTO> skiResortAndTimelineDTOs, Integer minSnowHeightValley, Integer minSnowHeightMountain) {
        List<SkiResortAndTimelineDTO> skiResortAndTimelineDTOS = skiResortAndTimelineDTOs;
        if(minSnowHeightMountain != 0) {
            skiResortAndTimelineDTOS = skiResortAndTimelineDTOs.stream()
                .filter(skiResortAndTimelineDTO ->
                        skiResortAndTimelineDTO.getShowHeightMountain() != null)
                .filter(skiResortAndTimelineDTO ->
                        skiResortAndTimelineDTO.getShowHeightMountain() >= minSnowHeightMountain).toList();
        }
        if(minSnowHeightValley != 0) {
            skiResortAndTimelineDTOS = skiResortAndTimelineDTOs.stream()
                .filter(skiResortAndTimelineDTO ->
                        skiResortAndTimelineDTO.getShowHeightValley() != null)
                .filter(skiResortAndTimelineDTO ->
                        skiResortAndTimelineDTO.getShowHeightValley() >= minSnowHeightValley).toList();
        }
        return skiResortAndTimelineDTOS;

    }

    public SingleResortResponseDTO findSingleResort(String apiKey, Long id, boolean current) throws MissingResourceException, InvalidApiKeyException {
        apiKeyService.validateAPIKey(apiKey);
        if(resortExists(id)) {
            if(current) {
                return skiResortRepository.findResortWithTimeline(id);
            } else {
                SkiResortAndAllTimelinesDTO skiResortAndAllTimelinesDTO = createAllTimelineDTO(id);
                return skiResortAndAllTimelinesDTO;
            }
        }
        else {
            throw new MissingResourceException("Das angeforderte Skigebiet existiert nicht", getClass().getName(), id.toString());
        }
    }

    private boolean resortExists(Long id) {
        return skiResortRepository.existsById(id);
    }

    private SkiResortAndAllTimelinesDTO createAllTimelineDTO(Long id) {
        SkiResort skiResort = skiResortRepository.findById(id).get();
        List<SkiResortTimeline> skiResortTimelines = skiResortTimelineRepository.findBySkiResort(skiResort);
        SkiResortAndAllTimelinesDTO skiResortAndAllTimelinesDTO = new SkiResortAndAllTimelinesDTO();
        populateDTO(skiResort, skiResortTimelines, skiResortAndAllTimelinesDTO);
        return skiResortAndAllTimelinesDTO;
    }

    private void populateDTO(SkiResort skiResort, List<SkiResortTimeline> skiResortTimelines, SkiResortAndAllTimelinesDTO skiResortAndAllTimelinesDTO) {
        skiResortAndAllTimelinesDTO.setSkiResortName(skiResort.getName());
        skiResortAndAllTimelinesDTO.setId(skiResort.getId());
        attachSnowDataEntries(skiResortTimelines, skiResortAndAllTimelinesDTO);
    }

    private void attachSnowDataEntries(List<SkiResortTimeline> skiResortTimelines, SkiResortAndAllTimelinesDTO skiResortAndAllTimelinesDTO) {
        List<SnowDataEntry> snowDataEntries = new ArrayList<>();
        for(SkiResortTimeline timeline : skiResortTimelines) {
            SnowDataEntry snowDataEntry = new SnowDataEntry(timeline.getDate(),
                                                            timeline.getSnowHeightMountain(),
                                                            timeline.getSnowHeightValley(),
                                                            timeline.getLiftsOpen(),
                                                            timeline.getPistesOpen());
            snowDataEntries.add(snowDataEntry);
        }
        skiResortAndAllTimelinesDTO.setSnowDataEntries(snowDataEntries);
    }


    public boolean isUpToDate(SkiResort skiResort) {
        SkiResortTimeline skiResortTimeline = skiResortTimelineRepository.findNewestEntry(skiResort);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        String lastEnteredDate;
        String today = dateFormatter.format(new Date());
        try {
            lastEnteredDate = dateFormatter.format(skiResortTimeline.getDate());
        } catch (NullPointerException exception) {
            return false;
        }
        if (lastEnteredDate.equals(today)) {
            return true;
        }
        return false;
    }

    public List<SkiResort> findAll() {
        return skiResortRepository.findAll();
    }
}