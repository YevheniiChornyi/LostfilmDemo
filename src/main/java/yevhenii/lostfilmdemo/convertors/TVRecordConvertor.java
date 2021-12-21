package yevhenii.lostfilmdemo.convertors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesDTO;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

@Slf4j
@Component
public class TVRecordConvertor implements Converter<TvSeriesRecord, TVSeries> {

    @Override
    public TVSeries convert(TvSeriesRecord queryResult) {
        if (queryResult.getTitle() == null) queryResult.setTitle("N/A");
        if (queryResult.getPlot() == null) queryResult.setPlot("N/A");
        if (queryResult.getYear() == null) queryResult.setYear(0);
        if (queryResult.getImdbrating() == null) queryResult.setImdbrating(.0);


        return
                TVSeries.builder()
                        .name(queryResult.getName())
                        .russianName(queryResult.getRussianName())
                        .link(queryResult.getLink())
                        .image(queryResult.getImage())
                        .episode(queryResult.getEpisode())
                        .season(queryResult.getSeason())
                        .lastUpdate(queryResult.getLastupdate())
                        .imdbEpisode(ImdbEpisodesDTO.builder()
                                .imdbRating(queryResult.getImdbrating().toString())
                                .plot(queryResult.getPlot())
                                .title(queryResult.getTitle())
                                .year(queryResult.getYear().toString())
                                .build())
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
        if (tvSeries.getImdbEpisode() == null) return record;
        record.setTitle(tvSeries.getImdbEpisode().getTitle());
        record.setYear(getYear(tvSeries));
        record.setPlot(tvSeries.getImdbEpisode().getPlot());
        record.setImdbrating(getImdbRating(tvSeries));

        return record;
    }

    private int getYear(TVSeries tvSeries) {
        try {
            return Integer.parseInt(tvSeries.getImdbEpisode().getYear());
        } catch (NumberFormatException e) {
            log.debug(e.getMessage());
        }
        return 0;
    }

    private double getImdbRating(TVSeries tvSeries) {
        try {
            return Double.parseDouble(tvSeries.getImdbEpisode().getImdbRating());
        } catch (NumberFormatException e) {
            log.debug(e.getMessage());
        }
        return .0;
    }
}
