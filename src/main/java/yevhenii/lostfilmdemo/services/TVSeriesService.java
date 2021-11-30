package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.Optional;

public interface TVSeriesService {

    void create(TVSeries tvSeries);

    Optional<TVSeries> findByName(String name);
}
