package yevhenii.lostfilmdemo.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.util.List;
import java.util.Optional;

@Service
class TVSeriesServiceImpl implements TVSeriesService {

    @Transactional
    @Override
    public void create(TVSeries tvSeries) {


    }

    @Override
    public Optional<TVSeries> findByName(String name) {

        return Optional.empty();
    }

    @Override
    public Optional<List<TVSeries>> findALl() {
        return Optional.empty();
    }
}
