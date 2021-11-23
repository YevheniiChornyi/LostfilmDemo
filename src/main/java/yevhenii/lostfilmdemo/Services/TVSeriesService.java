package yevhenii.lostfilmdemo.Services;

import yevhenii.lostfilmdemo.Entity.TVSeries;
import yevhenii.lostfilmdemo.RSS.FeedMessage;

public interface TVSeriesService {

    void addTVSeries(TVSeries tvSeries);

    TVSeries findByName(String name);
}
