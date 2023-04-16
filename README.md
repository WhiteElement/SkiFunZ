# SkiFunZ
SkiFunZ ist eine Web-Rest-API, die es erlaubt, Skigebiete in eine Watchlist hinzuzufügen und zu tracken. Dadurch ist es möglich jeden Tag topaktuelle und historische Daten zu Skigebieten (Schneehöhe Berg, Schneehöhe Tal, Betrieb, offene Lifte, offene Pisten) zu erhalten. Ist ein Skigebiet einmal zur Watchlist hinzugefügt, wird es ab diesem Zeitpunkt getrackt.
Die Application ist zweiteilig. Der eine Teil ist ein Webscraper, der die in der Datenbank befindlichen Skigebiete täglich scrapt/aktualisiert. Hierzu wird die Bergfex benutzt.
Der zweite ist die eigentliche API, die die Daten (authentifiziert) bereit stellt.

Zur Authentifizierung wird ein API-Access-Key benutzt, der sich nur vom Admin erstellen lässt.

## Administrator
Verwaltet wird die API aktuell nur serverseitig durch 4 shell-commands:

- `gebiete` - Zeigt alle Skigebiete an

- `gebiete-neu` - fügt ein neues Gebiet zur Datenbank/Watchlist hinzu

- `gebiete-update` - fürht den eigentlichen Scraper aus, der alle in der Datenbank befindlichen Gebiete updatet

- `user-neu` - legt einen neuen User mit einzigartigem API-Access-Key an.

## API
Die eigentliche Schnittstelle bietet 2 Endpunkte mit verschiedenen Filtermöglichkeiten:
Alle Requests müssen einen gültigen "api-access-key" im Header enthalten

- ``GET /api/v1/resorts`` - Zeigt alle Skigebiete an

  - ``GET .../resorts?sorted=true`` - Sortiert die Skigebiete in absteigender Reihenfolge nach der Schneehöhe im Gebiet

  - ``GET .../resorts?minSnowHeightMountain`` -  Filtert die Gebiete mit einer Mindestschneehöhe(Berg) heraus

  - ``GET .../resorts?minSnowHeightValley`` - Filtert die Gebiete mit einer Mindestschneehöhe(Tal) heraus

- ``GET /api/v1/resorts/{id}`` - Zeigt alle Daten zu einem Skigebiet an

&nbsp;
&nbsp;

## Technologien, die ich benutzt habe
- Spring Boot
- REST API Architektur
- Spring Shell
- Custom Error Handling
- JSoup
- Hibernate / Data JPA (Relationships & Entities)
- Access Key Authentication

&nbsp;
&nbsp;

## Demo
Request             |  Response
:-------------------------:|:-------------------------:
![skifunz](https://user-images.githubusercontent.com/78162213/232315637-bb4742ca-1c4a-450a-b489-57a64f0411e7.PNG)  |  ![skifunz1](https://user-images.githubusercontent.com/78162213/232315645-47f3f16a-b943-4038-b89f-5de238037903.PNG)

&nbsp;
&nbsp;

Request             |  Response
:-------------------------:|:-------------------------:
![skifunz2](https://user-images.githubusercontent.com/78162213/232315659-2b8abbf3-55c4-4ea8-9382-ebe3b28cb7b2.PNG)  |  ![skifunz4](https://user-images.githubusercontent.com/78162213/232315671-969f35ab-f607-46d8-8a1b-714909851a77.PNG)

&nbsp;
&nbsp;

Request             |  Response
:-------------------------:|:-------------------------:
![skifunz5](https://user-images.githubusercontent.com/78162213/232315756-57e4a272-124b-40e4-b6af-fa8136907a8e.PNG)  |  ![skifunz6](https://user-images.githubusercontent.com/78162213/232315759-8422243a-2017-4781-a39a-eeab9b47d828.PNG)



