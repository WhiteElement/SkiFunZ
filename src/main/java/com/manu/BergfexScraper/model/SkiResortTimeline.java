package com.manu.BergfexScraper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class SkiResortTimeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private SkiResort skiResort;

    private Date date;
    private Integer snowHeightMountain;
    private Integer snowHeightValley;
    private String operation;
    private String liftsOpen;
    private String pistesOpen;

    public SkiResortTimeline(){}

    public SkiResortTimeline(Long id, SkiResort skiResort, Date date, Integer snowHeightMountain, Integer snowHeightValley, String snowCondition, String operation, String liftsOpen, String pistesOpen) {
        Id = id;
        this.skiResort = skiResort;
        this.date = date;
        this.snowHeightMountain = snowHeightMountain;
        this.snowHeightValley = snowHeightValley;
        this.operation = operation;
        this.liftsOpen = liftsOpen;
        this.pistesOpen = pistesOpen;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public SkiResort getSkiResort() {
        return skiResort;
    }

    public void setSkiResort(SkiResort skiResort) {
        this.skiResort = skiResort;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSnowHeightMountain() {
        return snowHeightMountain;
    }

    public void setSnowHeightMountain(Integer snowHeightMountain) {
        this.snowHeightMountain = snowHeightMountain;
    }

    public Integer getSnowHeightValley() {
        return snowHeightValley;
    }

    public void setSnowHeightValley(Integer snowHeightValley) {
        this.snowHeightValley = snowHeightValley;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getLiftsOpen() {
        return liftsOpen;
    }

    public void setLiftsOpen(String liftsOpen) {
        this.liftsOpen = liftsOpen;
    }

    public String getPistesOpen() {
        return pistesOpen;
    }

    public void setPistesOpen(String pistesOpen) {
        this.pistesOpen = pistesOpen;
    }
}
