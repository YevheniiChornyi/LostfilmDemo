package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.rss.FeedMessage;

public interface ConvertService {


    TVSeries convert(FeedMessage feedMessage);
}
