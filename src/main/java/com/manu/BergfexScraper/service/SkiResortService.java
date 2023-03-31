package com.manu.BergfexScraper.service;

import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.model.SkiResortTimeline;
import com.manu.BergfexScraper.repository.SkiResortRepository;
import com.manu.BergfexScraper.repository.SkiResortTimelineRepository;
import com.manu.BergfexScraper.scraper.BergfexWebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SkiResortService {

    private BergfexWebScraper scraper;

    @Autowired
    SkiResortRepository skiResortRepository;

    @Autowired
    SkiResortTimelineRepository skiResortTimelineRepository;

    SkiResortService() {
        this.scraper = new BergfexWebScraper();
    }

    public String initNewResort(String urlstring) throws MalformedURLException {
        scraper.setUrl(new URL(urlstring));
        SkiResort skiResort = scraper.init();

        if(!alreadyExists(skiResort)) {
            save(skiResort);
            updateSnowTimeline(skiResort);
            return "gespeichert";
        }
        return "bereits in Datenbank";


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

    public List<SkiResort> findAll() {
        return skiResortRepository.findAll();
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
        if(lastEnteredDate.equals(today)) {
            return true;
        }
        return false;
    }
}