package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class TVSeriesServiceImpl implements TVSeriesService {

    private final TVSeriesRepositoryImpl tvSeriesRepository;
    private final TVRecordConvertor convertor;

    @Override
    @Transactional
    public Record save(TVSeries tvSeries) {

//        return tvSeriesRepository.read(tvSeries.getLink())
//                .map((a) -> this.update(tvSeries)).get();
//              .orElse(this.create(tvSeries));
        //SQL error

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

        tvSeriesRepository.create(convertor.createRecord(tvSeries));
        convertor.createRecord(tvSeries);
    }

    private void update(TVSeries tvSeries) {

        tvSeriesRepository.update(convertor.createRecord(tvSeries));
        convertor.createRecord(tvSeries);
    }


}
