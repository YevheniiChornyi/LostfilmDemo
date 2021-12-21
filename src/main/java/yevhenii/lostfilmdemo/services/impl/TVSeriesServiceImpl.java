package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.kafka.TVSeriesSender;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TVSeriesServiceImpl implements TVSeriesService {

    private final TVSeriesRepositoryImpl tvSeriesRepository;
    private final TVRecordConvertor convertor;
    private final TVSeriesSender sender;
    @Value("${lostfilm.topic}")
    private final String topic;


    @Override
    @Transactional
    public void save(TVSeries tvSeries) {

        if (tvSeriesRepository.read(tvSeries.getLink()).isEmpty()) this.create(tvSeries);
        else this.update(tvSeries);
    }

    @Override
    public void delete(String link) {

        tvSeriesRepository.delete(link);
    }

    @Override
    public Optional<TVSeries> find(String link) {

        return tvSeriesRepository.read(link)
                .map(a -> convertor.convert((TvSeriesRecord) a));
    }

    @Override
    public List<TVSeries> findAll() {

        return tvSeriesRepository.readAll()
                .stream()
                .map((a) -> convertor.convert((TvSeriesRecord) a))
                .collect(Collectors.toList());
    }

    private void create(TVSeries tvSeries) {

        tvSeriesRepository.create(convertor.createRecord(tvSeries));
        sender.send(topic, tvSeries);
    }

    private void update(TVSeries tvSeries) {

        sender.send(topic, tvSeries);
    }


}
