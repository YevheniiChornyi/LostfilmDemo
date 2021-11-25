package yevhenii.lostfilmdemo.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.services.TVSeriesService;

@Service
class TVSeriesServiceImpl implements TVSeriesService {

    @Transactional
    @Override
    public void add(TVSeries tvSeries) {

    }

    @Override
    public TVSeries findByName(String name) {

        return null;
    }
}
