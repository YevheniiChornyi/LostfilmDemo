package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {

    void save(TVSeries tvSeries);

    void delete(String link);

    Optional<TVSeries> find(String link);

    List<TVSeries> findAll();
}
