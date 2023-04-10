package com.manu.BergfexScraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BergfexWebScraperTest {

    @Test
    public void shouldOnlyReturnTheFirstNumber() {
        String html = "<dt class=\"big\">Berg <span class=\"less-important\">(Piste, 2.262m)</span></dt>\n" +
                "<dd class=\"big\">\n" +
                "70 cm<div class=\"default-size\">neu: 5 cm</div></dd>\n" +
                "<dt>Schneezustand</dt>\n" +
                "<dd>\n" +
                "hart, teilweise Sulz</dd>\n" +
                "<dt>Letzter Schneefall Region</dt>\n" +
                "<dd>Sa, 08.04. - Tal: 28.03.2023\n" +
                "</dd>";

        Document doc = Jsoup.parse(html);

        Element dtElement = doc.select("dt:contains(Berg)").first();
        if (dtElement != null) {
            Element ddElement = dtElement.nextElementSibling();
            if (ddElement != null) {
                String textContent = ddElement.ownText(); // Only the text within dd element
                assertTrue(textContent.equals("70 cm"));
            }
        }
    }

}