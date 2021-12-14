package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.controllers.ImdbHolderClient;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesHolder;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TVSeriesServiceImpl implements TVSeriesService {

    private final ImdbHolderClient imdbHolderClient;
    private final TVSeriesRepositoryImpl tvSeriesRepository;
    private final TVRecordConvertor convertor;

    @Override
    @Transactional
    public Record save(TVSeries tvSeries) {

        if (tvSeriesRepository.read(tvSeries.getLink()).isEmpty()) this.create(tvSeries);
        else this.update(tvSeries);
        return convertor.createRecord(tvSeries);
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

        tvSeriesRepository.create(convertor.createRecord(createImdbPart(tvSeries)));
//        convertor.createRecord(tvSeries);
    }
//TODO kafka event idk what for
    private void update(TVSeries tvSeries) {

        tvSeriesRepository.update(convertor.createRecord(createImdbPart(tvSeries)));
//        convertor.createRecord(tvSeries);
    }

    private TVSeries createImdbPart(TVSeries tvSeries){

        ImdbEpisodesHolder holder= imdbHolderClient.getSeason(
                imdbHolderClient.getSeries(tvSeries.getName())
                .getResults()
                .get(0)
                .getId(),
                tvSeries.getSeason());
        holder.getEpisodes().stream()
                .filter(i->i.getEpisodeNumber() == tvSeries.getEpisode())
                .findFirst()
                .ifPresent(tvSeries::setImdbEpisode);
        return tvSeries;
    }


}
