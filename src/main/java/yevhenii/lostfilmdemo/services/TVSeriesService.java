package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {

    void create(TVSeries tvSeries);

    Optional<TVSeries> findByName(String name);

    Optional<List<TVSeries>> findALl();
}
