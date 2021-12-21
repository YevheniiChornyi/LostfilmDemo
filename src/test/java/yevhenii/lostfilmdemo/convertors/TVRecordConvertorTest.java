package yevhenii.lostfilmdemo.convertors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yevhenii.lostfilmdemo.entity.TVSeries;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TVRecordConvertorTest {
    @Autowired
    private TVRecordConvertor convertor;

    @Test
    void mirror(){

        TVSeries tvSeries = TVSeries.builder().link("link1").season(1).name("name1").russianName("rname1").image("image1").episode(1).lastUpdate("1").build();
        TVSeries series = convertor.convert(convertor.createRecord(tvSeries));
        series.setImdbEpisode(null);
        assertThat(tvSeries).isEqualTo(series);
    }
}