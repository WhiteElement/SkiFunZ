package com.manu.BergfexScraper.dto;

import com.manu.BergfexScraper.auxillary.SnowDataEntry;

import java.util.Date;
import java.util.List;

public class SkiResortAndAllTimelinesDTO implements SingleResortResponseDTO {

    private Long Id;
    private String skiResortName;

    List<SnowDataEntry> snowDataEntries;

    public SkiResortAndAllTimelinesDTO() {
    }

    public SkiResortAndAllTimelinesDTO(Long id, String skiResortName, List<SnowDataEntry> snowDataEntries) {
        Id = id;
        this.skiResortName = skiResortName;
        this.snowDataEntries = snowDataEntries;
    }

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

    public List<SnowDataEntry> getSnowDataEntries() {
        return snowDataEntries;
    }

    public void setSnowDataEntries(List<SnowDataEntry> snowDataEntries) {
        this.snowDataEntries = snowDataEntries;
    }
}
