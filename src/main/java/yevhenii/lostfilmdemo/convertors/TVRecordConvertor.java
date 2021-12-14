package yevhenii.lostfilmdemo.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

@Component
public class TVRecordConvertor implements Converter<TvSeriesRecord, TVSeries> {

//TODO update imdb
    @Override
    public TVSeries convert(TvSeriesRecord queryResult) {
        return
                TVSeries.builder()
                        .name(queryResult.getName())
                        .russianName(queryResult.getRussianName())
                        .link(queryResult.getLink())
                        .image(queryResult.getImage())
                        .episode(queryResult.getEpisode())
                        .season(queryResult.getSeason())
                        .lastUpdate(queryResult.getLastupdate())
                        .build()
                ;
    }

    public TvSeriesRecord createRecord(TVSeries tvSeries) {

        TvSeriesRecord record = new TvSeriesRecord();

        record.setImage(tvSeries.getImage());
        record.setEpisode(tvSeries.getEpisode());
        record.setSeason(tvSeries.getSeason());
        record.setName(tvSeries.getName());
        record.setRussianName(tvSeries.getRussianName());
        record.setLink(tvSeries.getLink());
        record.setLastupdate(tvSeries.getLastUpdate());

        return record;
    }
}
