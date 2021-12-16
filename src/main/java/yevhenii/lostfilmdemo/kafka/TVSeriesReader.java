package yevhenii.lostfilmdemo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.controllers.ImdbClient;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesDTO;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.repository.TVSeriesRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class TVSeriesReader {
    @Value("${omdb.api.key}")
    private final String apiKey;
    private final ImdbClient imdbClient;
    private final TVSeriesRepository repository;
    private final TVRecordConvertor convertor;

    @KafkaListener(topics = {"${lostfilm.topic}"})
    public void receive(TVSeries record) {
        log.info("received: {}", record.toString());
        repository.update(convertor.createRecord(createOmdbPart(record)));
    }

    private TVSeries createOmdbPart(TVSeries tvSeries) {

        ImdbEpisodesDTO imdbSeries = imdbClient.getEpisode(apiKey, tvSeries.getName(), tvSeries.getSeason(), tvSeries.getEpisode());
        log.info(imdbSeries.toString());
        if (imdbSeries.getImdbID() != null) {
            tvSeries.setImdbEpisode(imdbSeries);
            return tvSeries;

        }
        return tvSeries;

    }
    //TODO Caused by: org.apache.kafka.common.errors.TimeoutException: Topic lostfilmTvSeries not present in metadata after 60000 ms.
}
