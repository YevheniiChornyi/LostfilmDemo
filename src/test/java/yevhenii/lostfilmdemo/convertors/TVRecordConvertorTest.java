package yevhenii.lostfilmdemo.convertors;

import org.junit.jupiter.api.Test;
import yevhenii.lostfilmdemo.entity.TVSeries;

import static org.assertj.core.api.Assertions.assertThat;

class TVRecordConvertorTest {
    private final TVRecordConvertor convertor = new TVRecordConvertor();

    @Test
    void mirror(){

        TVSeries tvSeries = TVSeries.builder().link("link1").season(1).name("name1").russianName("rname1").image("image1").episode(1).lastUpdate("1").build();
        TVSeries series = convertor.convert(convertor.createRecord(tvSeries));
        series.setImdbEpisode(null);
        assertThat(tvSeries).isEqualTo(series);
    }
}