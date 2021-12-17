package yevhenii.lostfilmdemo.convertors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TVSeriesConvertorTest {

    @Autowired
    private TVSeriesConvertor convertor;

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
        assertEquals(tvSeries.getName(), "It's Always Sunny in Philadelphia");
        assertEquals(tvSeries.getRussianName(), "Всегда солнечно . Шайка заменяет Ди макакой. ");
        assertEquals(tvSeries.getLink(), message0.getLink());
        assertEquals(tvSeries.getLastUpdate(), message0.getPubDate());
    }
}