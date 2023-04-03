package com.manu.BergfexScraper.auxillary;

import java.util.Date;

public class SnowDataEntry {

    private Date updatedOn;
    private Integer showHeightMountain;
    private Integer showHeightValley;
    private String liftsOpen;
    private String pistesOpen;

    SnowDataEntry() {}

    public SnowDataEntry(Date updatedOn, Integer showHeightMountain, Integer showHeightValley, String liftsOpen, String pistesOpen) {
        this.updatedOn = updatedOn;
        this.showHeightMountain = showHeightMountain;
        this.showHeightValley = showHeightValley;
        this.liftsOpen = liftsOpen;
        this.pistesOpen = pistesOpen;
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
}
