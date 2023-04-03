package com.manu.BergfexScraper.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SkiResort {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private URL url;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "skiResort", cascade = CascadeType.ALL)
    private List<SkiResortTimeline> skiResortTimelines= new ArrayList<>();

    public SkiResort(){}

    public SkiResort(Long id, URL url, String name) {
        Id = id;
        this.url = url;
        this.name = name;
    }

    public SkiResortTimeline latestSnowHeightMountain() {
        if(skiResortTimelines.size() > 0) {
            int size = skiResortTimelines.size();
            return skiResortTimelines.get(size-1);
        }
        return null;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkiResortTimeline> getSkiResortTimelines() {
        return skiResortTimelines;
    }

    public void setSkiResortTimelines(List<SkiResortTimeline> skiResortTimelines) {
        this.skiResortTimelines = skiResortTimelines;
    }
}
