package yevhenii.lostfilmdemo.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesDTO;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.Objects;

@Component
public class TVRecordConvertor implements Converter<TvSeriesRecord, TVSeries> {

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
        if (!Objects.equals(tvSeries.getImdbEpisode().getYear(), "N/A"))
            record.setYear(Integer.parseInt(tvSeries.getImdbEpisode().getYear()));
        record.setPlot(tvSeries.getImdbEpisode().getPlot());
        if (!Objects.equals(tvSeries.getImdbEpisode().getImdbRating(), "N/A"))
            record.setImdbrating(Double.parseDouble(tvSeries.getImdbEpisode().getImdbRating()));

        return record;
    }
}
