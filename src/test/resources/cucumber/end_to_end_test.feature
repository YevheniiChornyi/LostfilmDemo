Feature: TVSeries update

  Scenario: find info about latest TV series
    Given I go mock tv series sites
    When I go to find new series
    Then I should get TVSeries
    * put it into db
    * send message, that we should find info from imdb and also put it to db
