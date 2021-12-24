# encoding: utf-8
Feature: TVSeries update

  Scenario: find info about latest TV series
    Given I mocking Lostfilm RSS feed response
    When I start scheduled process to retrieve series from Lostfilm
    And I wait scheduled process to complete
    Then I should get TVSeries
    And I except following series in DB:
      | name          | russianName                     | image                                            | season | episode | link                                                                     | lastUpdate                      |
      | 1883          | 1883 . За спиной - обрыв.       | static.lostfilm.top/Images/634/Posters/image.jpg | 1      | 2       | https://www.lostfilmtv1.site/mr/series/1883/season_1/episode_2/          | Thu, 23 Dec 2021 20:57:00 +0000 |
      | Dix pour cent | Десять процентов . Сесиль.      | static.lostfilm.top/Images/613/Posters/image.jpg | 1      | 1       | https://www.lostfilmtv1.site/mr/series/Dix_pour_cent/season_1/episode_1/ | Thu, 23 Dec 2021 19:01:10 +0000 |
      | True Story    | Реальная история . Вывоз грека. | static.lostfilm.top/Images/638/Posters/image.jpg | 1      | 2       | https://www.lostfilmtv1.site/mr/series/True_Story/season_1/episode_2/    | Thu, 23 Dec 2021 13:59:52 +0000 |
      | Hellbound     | Зов ада . 6 серия.              | static.lostfilm.top/Images/631/Posters/image.jpg | 1      | 6       | https://www.lostfilmtv1.site/mr/series/Hellbound/season_1/episode_6/     | Wed, 22 Dec 2021 19:21:25 +0000 |
      | 1883          | 1883 . 1883.                    | static.lostfilm.top/Images/634/Posters/image.jpg | 1      | 1       | https://www.lostfilmtv1.site/mr/series/1883/season_1/episode_1/          | Tue, 21 Dec 2021 20:59:00 +0000 |
    And I expect following ImDB info to be populated:
      | title              | rating | year | plot                                                                                                                                               |
      | Behind Us, A Cliff | 8.3    | 2021 | Thomas and Shea recruit James and some local cowboys to corral a herd of cattle.                                                                   |
      | N/A                | 0.0    | 0    | N/A                                                                                                                                                |
      | Episode #1.2       | 0.0    | 0    | N/A                                                                                                                                                |
      | Episode #1.6       | 7.6    | 2021 | With detrimental information hanging over its head, The New Truth scrambles to protect its power by preventing the public from learning the truth. |
      | 1883               | 8.3    | 2021 | The Dutton family embarks westward through the last bastion of untamed America.                                                                    |