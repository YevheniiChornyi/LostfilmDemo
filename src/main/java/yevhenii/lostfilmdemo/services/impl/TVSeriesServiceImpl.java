package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.repository.JooqRepository;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TVSeriesServiceImpl implements TVSeriesService {

    private final JooqRepository<String> schedulerRepository;

    @Override
    @Transactional
    public void save(TVSeries tvSeries) {

        if(isExist(tvSeries.getLink()))
            schedulerRepository.update(createRecord(tvSeries), tvSeries.getLink());
        else schedulerRepository.create(createRecord(tvSeries));
    }

    @Override
    public TVSeries find(String link) {
        Optional<Record> optional = schedulerRepository.read(link);
        if (optional.isEmpty()) throw new IllegalArgumentException("Cant find series by link: " + link);
        return convertQueryResultToModelObject((TvSeriesRecord) optional.get());
    }

    @Override
    public List<TVSeries> findAll() {
        return schedulerRepository.readAll()
                .stream()
                .map((a) -> convertQueryResultToModelObject((TvSeriesRecord) a))
                .collect(Collectors.toList());
    }

    private boolean isExist(String link) {

        return schedulerRepository.read(link).isPresent();
    }

    private TVSeries convertQueryResultToModelObject(TvSeriesRecord queryResult) {
        return
                TVSeries.builder()
                        .name(queryResult.getName())
                        .russianName(queryResult.getRussianName())
                        .link(queryResult.getLink())
                        .image(queryResult.getImage())
                        .episode(queryResult.getEpisode())
                        .season(queryResult.getSeason())
                        .build()
                ;
    }
    private TvSeriesRecord createRecord(TVSeries tvSeries) {

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
