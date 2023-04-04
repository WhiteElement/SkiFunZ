package com.manu.BergfexScraper.commands;

import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.service.SkiResortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ScrapeCommand {

    @Autowired
    SkiResortService skiResortService;

//    @ShellMethod(key = "gebiet-neu", value = "speichert ein neues Skigebiet")
//    public String createNewSkiResort(@ShellOption(defaultValue = "") String url) throws MalformedURLException {
//        String urlstring;
//        if(url.equals("")) {
//            System.out.println("Bergfex Link des Skigebiets einfügen: ");
//            Scanner scanner = new Scanner(System.in);
//            urlstring = scanner.nextLine();
//        } else {
//            urlstring = url;
//        }
//        return skiResortService.initNewResort(urlstring);
//    }

    @ShellMethod(key = "gebiet-update", value = "führt ein Update für alle in der Datenbank befindlichen Skigebiete durch")
    public String updateSkiResortSnowData() throws MalformedURLException {
        List<SkiResort> skiResorts = skiResortService.findAll();
        if(skiResorts.size() == 0) {
            return "Noch keine Skigebiete hinzugefügt";
        } else {
            for(SkiResort skiResort : skiResorts) {
                if(!skiResortService.isUpToDate(skiResort)) {
                    skiResortService.updateSnowTimeline(skiResort);
                }
            }
            return "Alle Skigebiete("
                    + skiResorts.size()
                    + ") upgedated";
        }
    }

//    @ShellMethod(key = "gebiete", value= "zeigt alle Gebiete in der Datenbank an")
//    public String showAllResorts() {
//        String result = "Skigebiete \n" +
//                        "---------- \n";
//        List<SkiResort> skiResorts = skiResortService.findAll(false);
//        if(skiResorts.size() == 0) {
//            return "Noch keine Skigebiete hinzugefügt";
//        } else {
//            int counter = 1;
//            for(SkiResort skiResort : skiResorts) {
//                result += counter + ".) "
//                        + skiResort.getName()
//                        + "\n";
//                counter++;
//            }
//            return result;
//        }
//    }
}
