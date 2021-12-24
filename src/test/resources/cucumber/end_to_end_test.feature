# encoding: utf-8
Feature: TVSeries update

  Scenario: find info about latest TV series
    Given I go mock tv series sites
    When I go to find new series
    Then I should get TVSeries
    And put tvSeries into db:
      | name            | russianName                      | image                                            | season| episode| link                                                                    |lastUpdate                        |
      | 1883            | 1883 . За спиной - обрыв.        | static.lostfilm.top/Images/634/Posters/image.jpg | 1     | 2      | https://www.lostfilmtv1.site/mr/series/1883/season_1/episode_2/         | Thu, 23 Dec 2021 20:57:00 +0000  |
      | Dix pour cent   | Десять процентов . Сесиль.       | static.lostfilm.top/Images/613/Posters/image.jpg | 1     | 1      | https://www.lostfilmtv1.site/mr/series/Dix_pour_cent/season_1/episode_1/| Thu, 23 Dec 2021 19:01:10 +0000  |
      | True Story      | Реальная история . Вывоз грека.  | static.lostfilm.top/Images/638/Posters/image.jpg | 1     | 2      | https://www.lostfilmtv1.site/mr/series/True_Story/season_1/episode_2/   | Thu, 23 Dec 2021 13:59:52 +0000  |
      | Hellbound       | Зов ада . 6 серия.               | static.lostfilm.top/Images/631/Posters/image.jpg | 1     | 6      | https://www.lostfilmtv1.site/mr/series/Hellbound/season_1/episode_6/    | Wed, 22 Dec 2021 19:21:25 +0000  |
      | 1883            | 1883 . 1883.                     | static.lostfilm.top/Images/634/Posters/image.jpg | 1     | 1      | https://www.lostfilmtv1.site/mr/series/1883/season_1/episode_1/         | Tue, 21 Dec 2021 20:59:00 +0000  |
    And send message, that we should find info from imdb and also put updated tvSeries to db

