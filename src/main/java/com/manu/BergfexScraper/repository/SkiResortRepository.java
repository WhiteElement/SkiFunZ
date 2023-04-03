package com.manu.BergfexScraper.repository;

import com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO;
import com.manu.BergfexScraper.model.SkiResort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.URL;
import java.util.Date;
import java.util.List;

public interface SkiResortRepository extends JpaRepository<SkiResort, Long> {

    boolean existsByName(String name);

    SkiResort findByUrl(URL url);

    @Query("SELECT new com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO(" +
            "r.Id, r.name, t.date, t.snowHeightMountain, t.snowHeightValley, t.liftsOpen, t.pistesOpen) " +
            "FROM SkiResort r LEFT JOIN SkiResortTimeline t " +
            "ON r.Id = t.skiResort.Id " +
            "where t.date = (SELECT MAX(t2.date) FROM SkiResortTimeline t2 WHERE t2.skiResort = r)")
    List<SkiResortAndTimelineDTO> findAllResortsWithTimeline();

    @Query("SELECT new com.manu.BergfexScraper.dto.SkiResortAndTimelineDTO(" +
            "r.Id, r.name, t.date, t.snowHeightMountain, t.snowHeightValley, t.liftsOpen, t.pistesOpen) " +
            "FROM SkiResort r LEFT JOIN SkiResortTimeline t " +
            "ON r.Id = t.skiResort.Id " +
            "WHERE t.date = (SELECT MAX(t2.date) FROM SkiResortTimeline t2 WHERE t2.skiResort = r) " +
            "ORDER BY t.snowHeightMountain DESC")
    List<SkiResortAndTimelineDTO> findAllResortsWithTimelineOrderedBySnowMountainHeight();

//    private Long Id;
//    private String skiResortName;
//    private Date updatedOn;
//    private Integer showHeightMountain;
//    private Integer showHeightValley;
//    private String liftsOpen;
//    private String pistesOpen;


//    @Query("SELECT new com.manu.BergfexScraper.dto.SkiResortDTO(sr, srt) FROM SkiResort sr LEFT JOIN sr.skiResortTimelines srt")
//    List<SkiResortDTO> findAllWithTimeLine();
//    @Query("SELECT DISTINCT sr FROM SkiResort sr LEFT JOIN FETCH sr.skiResortTimelines srt")
//    List<SkiResort> findallwithtimeline2();
}
