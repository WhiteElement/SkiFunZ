package com.manu.BergfexScraper.repository;

import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.model.SkiResortTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkiResortTimelineRepository extends JpaRepository<SkiResortTimeline, Long> {
    @Query("from SkiResortTimeline s where s.skiResort = :resortparam order by date desc limit 1")
    SkiResortTimeline findNewestEntry(@Param("resortparam") SkiResort skiResort);

}
