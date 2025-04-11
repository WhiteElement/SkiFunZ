package com.manu.BergfexScraper.commands;

import com.manu.BergfexScraper.model.APIKey;
import com.manu.BergfexScraper.model.SkiResort;
import com.manu.BergfexScraper.service.APIKeyService;
import com.manu.BergfexScraper.service.SkiResortService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ScrapeCommand {

    private final APIKeyService apiKeyService;
    private final SkiResortService skiResortService;

    protected ScrapeCommand(APIKeyService apiKeyService, SkiResortService skiResortService) {
        this.apiKeyService = apiKeyService;
        this.skiResortService = skiResortService;
    }

    @ShellMethod(key = "gebiete-neu", value = "speichert ein neues Skigebiet")
    public String createNewSkiResort(@ShellOption(defaultValue = "") String url) throws MalformedURLException {
        String urlstring;
        if(url.isEmpty()) {
            System.out.println("Bergfex Link des Skigebiets einfügen: ");
            Scanner scanner = new Scanner(System.in);
            urlstring = scanner.nextLine();
        } else {
            urlstring = url;
        }
        return skiResortService.initNewResort(urlstring);
    }

    @ShellMethod(key = "gebiete-update", value = "führt ein Update für alle in der Datenbank befindlichen Skigebiete durch")
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

    @ShellMethod(key = "users", value = "zeigt alle User mit deren API-Keys an")
    public String showAllUsersAndKeys() {
        var res = apiKeyService.getAll();

        String[] formatted = res.stream()
            .map(apiKey -> {
                return String.format("User: %s, Key: %s", apiKey.getUsername(), apiKey.getKeyValue());
            }).toArray(String[]::new);

        return String.join("\n", formatted);
    }

    @ShellMethod(key = "user-neu", value = "legt neuen User mit API-Key an")
    public String createNewUserWithKey(@ShellOption(defaultValue = "" ) String username) {
        if(username.equals("")) {
            return "keinen Username angegeben! \n" +
                    "Versuche: 'create USERNAME'";
        } else {
            if(!apiKeyService.userNameAlreadyExists(username)) {
                return "Username ist bereits vergeben";
            } else {
                APIKey apiKey = apiKeyService.createNew(username);
                return "Neuer User + Key angelegt:\n" +
                        "-------------------------\n" +
                        "Username: " + apiKey.getUsername() + "\n" +
                        "Key: " + apiKey.getKeyValue();
            }
        }
    }

    @ShellMethod(key = "gebiete", value= "zeigt alle Gebiete in der Datenbank an")
    public String showAllResorts() {
        String result = "Skigebiete \n" +
                        "---------- \n";
        List<SkiResort> skiResorts = skiResortService.findAll();
        if(skiResorts.size() == 0) {
            return "Noch keine Skigebiete hinzugefügt";
        } else {
            int counter = 1;
            for(SkiResort skiResort : skiResorts) {
                result += counter + ".) "
                        + skiResort.getName()
                        + "\n";
                counter++;
            }
            return result;
        }
    }
}
