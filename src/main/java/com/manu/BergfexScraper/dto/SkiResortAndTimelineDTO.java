package com.manu.BergfexScraper.dto;

import java.util.Date;

public class SkiResortAndTimelineDTO {

    private Long Id;
    private String skiResortName;
    private Date updatedOn;
    private Integer showHeightMountain;
    private Integer showHeightValley;
    private String liftsOpen;
    private String pistesOpen;

    SkiResortAndTimelineDTO() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSkiResortName() {
        return skiResortName;
    }

    public void setSkiResortName(String skiResortName) {
        this.skiResortName = skiResortName;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getShowHeightMountain() {
        return showHeightMountain;
    }

    public void setShowHeightMountain(Integer showHeightMountain) {
        this.showHeightMountain = showHeightMountain;
    }

    public Integer getShowHeightValley() {
        return showHeightValley;
    }

    public void setShowHeightValley(Integer showHeightValley) {
        this.showHeightValley = showHeightValley;
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

    public SkiResortAndTimelineDTO(Long id, String skiResortName, Date updatedOn, Integer showHeightMountain, Integer showHeightValley, String liftsOpen, String pistesOpen) {
        Id = id;
        this.skiResortName = skiResortName;
        this.updatedOn = updatedOn;
        this.showHeightMountain = showHeightMountain;
        this.showHeightValley = showHeightValley;
        this.liftsOpen = liftsOpen;
        this.pistesOpen = pistesOpen;
    }
}
