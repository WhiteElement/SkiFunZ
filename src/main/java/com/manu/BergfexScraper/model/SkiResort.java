package com.manu.BergfexScraper.model;

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

    @OneToMany(mappedBy = "skiResort", cascade = CascadeType.ALL)
    private List<SkiResortTimeline> skiResortTimelines= new ArrayList<>();

    public SkiResort(){}

    public SkiResort(Long id, URL url, String name) {
        Id = id;
        this.url = url;
        this.name = name;
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
}
