package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;

public interface TVSeriesService {

    void save(TVSeries tvSeries);

    TVSeries find(String link);

    List<TVSeries> findAll();
}
