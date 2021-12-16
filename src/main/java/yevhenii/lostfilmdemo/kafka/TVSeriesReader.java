package yevhenii.lostfilmdemo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.controllers.OmdbHolderClient;
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
    private final OmdbHolderClient omdbHolderClient;
    private final TVSeriesRepository repository;
    private final TVRecordConvertor convertor;

    @KafkaListener(topics = {"${lostfilm.topic}"})
    public void receive(ConsumerRecord<String, TVSeries> consumerRecord) {
        log.info("received: {}", consumerRecord.toString());
        TVSeries tvSeries = createOmdbPart((consumerRecord.value()));
        repository.update(convertor.createRecord(tvSeries));
    }

    private TVSeries createOmdbPart(TVSeries tvSeries) {

        ImdbEpisodesDTO imdbSeries = omdbHolderClient.getEpisode(apiKey, tvSeries.getName(), tvSeries.getSeason(), tvSeries.getEpisode());
        log.info(imdbSeries.toString());
        if (imdbSeries.getImdbID() != null) {
            tvSeries.setImdbEpisode(imdbSeries);
            return tvSeries;

        }
        return tvSeries;

    }
    //TODO Caused by: org.apache.kafka.common.errors.TimeoutException: Topic lostfilmTvSeries not present in metadata after 60000 ms.
}
