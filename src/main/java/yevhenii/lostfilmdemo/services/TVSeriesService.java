package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;

public interface TVSeriesService {

    void save(TVSeries tvSeries);

    boolean delete (String link);

    TVSeries find(String link);

    List<TVSeries> findAll();
}
