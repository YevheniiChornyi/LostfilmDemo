package yevhenii.lostfilmdemo.services;

import org.jooq.Record;
import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {

    Record save(TVSeries tvSeries);

    void delete(String link);

    Optional<TVSeries> find(String link);

    List<TVSeries> findAll();
}
