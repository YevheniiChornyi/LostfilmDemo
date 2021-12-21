package yevhenii.lostfilmdemo.convertors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
class TVSeriesConvertorTest {

    private final TVSeriesConvertor convertor = new TVSeriesConvertor();

    @Test
    void convert() {
        FeedMessage message0 = FeedMessage.builder()
                .link("https://www.lostfilmtv1.site/mr/series/Its_Always_Sunny_in_Philadelphia/season_15/episode_4/")
                .description("<![CDATA[ <img src=\"//static.lostfilm.top/Images/125/Posters/image.jpg\" alt=\"\" /><br /> ]]>\n")
                .pubDate("Fri, 17 Dec 2021 07:55:25 +0000")
                .title("Всегда солнечно (It's Always Sunny in Philadelphia). Шайка заменяет Ди макакой. (S15E04)")
                .build();

        TVSeries tvSeries = convertor.convert(message0);
        assertNotNull(tvSeries);
        assertEquals(tvSeries.getEpisode(), 4);
        assertEquals(tvSeries.getSeason(), 15);
        assertEquals(tvSeries.getImage(), "static.lostfilm.top/Images/125/Posters/image.jpg");
        Assertions.assertThat(tvSeries.getName()).contains("It's Always Sunny in Philadelphia");
        Assertions.assertThat(tvSeries.getRussianName()).contains("Всегда солнечно").contains("Шайка заменяет Ди макакой");
        assertEquals(tvSeries.getLink(), message0.getLink());
        assertEquals(tvSeries.getLastUpdate(), message0.getPubDate());
    }
}