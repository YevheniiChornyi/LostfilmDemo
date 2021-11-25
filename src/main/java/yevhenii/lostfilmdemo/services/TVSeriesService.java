package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;

public interface TVSeriesService {

    void add(TVSeries tvSeries);

    TVSeries findByName(String name);
}
