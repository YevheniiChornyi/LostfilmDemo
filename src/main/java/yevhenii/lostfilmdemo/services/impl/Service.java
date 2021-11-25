package yevhenii.lostfilmdemo.services.impl;

import yevhenii.lostfilmdemo.services.ConvertService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

class Service {

    final private String url = "https://www.lostfilm.tv/rss.xml";

    //FeedService feedService = new RssFeedParser(url);
    TVSeriesService tvSeriesService = new TVSeriesServiceImpl();
    ConvertService convertService = new ConvertServiceImpl();

    void start() {
//        convertService.splitFeedMessages(feedService.readFeed());

    }
}
