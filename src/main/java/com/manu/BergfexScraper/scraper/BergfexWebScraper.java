package com.manu.BergfexScraper.scraper;

import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.model.SkiResortTimeline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class BergfexWebScraper {

    private URL url;
    private Document document;

    public BergfexWebScraper(){}

    public BergfexWebScraper(String url) throws MalformedURLException {
        this.url = normalizeUrl(url);
    }

    public SkiResort init() {
        try {
            this.document = Jsoup.connect(url.toString()).get();
            SkiResort skiResort = new SkiResort();
            String headingTextContent = document.select("h1").text();
            skiResort.setName(headingTextContent.replace("Skigebiet ", ""));
            skiResort.setUrl(url);
            return skiResort;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL normalizeUrl(String url) throws MalformedURLException {
        URL normalizedUrl = new URL(url);
        String path = getFirstPath(normalizedUrl);

        return new URL(normalizedUrl.getProtocol()
                        + "://"
                        + normalizedUrl.getHost()
                        + path);
    }

    private String getFirstPath(URL normalizedUrl) {
        String path = normalizedUrl.getPath();
        if(StringUtils.countOccurrencesOf(path, "/") < 2) {
            return path;
        }
        int pathIndex = path.indexOf("/", 1);
        return path.substring(0, pathIndex);
    }

    public SkiResortTimeline updateSnowData(SkiResort skiResort) {

        String snowHeightsUrl = skiResort.getUrl().toString() + "/schneebericht";

        try {
            this.document = Jsoup.connect(snowHeightsUrl).get();

            SkiResortTimeline skiResortTimeline = new SkiResortTimeline();
            skiResortTimeline.setDate(new Date());
            skiResortTimeline.setSnowHeightMountain(getValueFor("Berg"));
            skiResortTimeline.setSnowHeightValley(getValueFor("Tal"));
            skiResortTimeline.setOperation(getTextFor("Betrieb"));
            skiResortTimeline.setLiftsOpen(getTextFor("Offene Lifte"));
            skiResortTimeline.setPistesOpen(getTextFor("Offene Pisten"));
            skiResortTimeline.setSkiResort(skiResort);
            return skiResortTimeline;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTextFor(String tableString) {
        Elements resultElements = findElements(tableString);
        if(resultElements.size() == 0) {
            return null;
        }
        if(resultElements.next().text() == "-") {
            return null;
        }
        return resultElements.next().text();
    }

    private Elements findElements(String tableString) {
        String queryString = "dt:contains(" + tableString + ")";
        return document.select(queryString);
    }

    private Integer getValueFor(String tableString) {
        Elements resultElements = findElements(tableString);
        if(resultElements.size() == 0) {
            return null;
        }
        if(resultElements.next().text() == "-") {
            return null;
        }
        if(resultElements.size() > 1) {
            return convertToInt(resultElements.next().first().text());
        }
        return convertToInt(resultElements.next().text());
    }

    private Integer convertToInt(String text) {
        String numbersRemoved = text.replaceAll("[^0-9]","");
        try {
            return Integer.parseInt(numbersRemoved);
        } catch (Exception exception) {
            return null;
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
