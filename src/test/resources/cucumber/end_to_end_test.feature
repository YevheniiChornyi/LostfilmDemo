Feature: TVSeries update

  Scenario: find info about latest TV series
    When I go to lostfilm.rss
    Then I should get TVSeries
    * put it into db
    * send message, that we should find info from imdb and also put it to db
