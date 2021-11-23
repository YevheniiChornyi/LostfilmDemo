package yevhenii.lostfilmdemo.Services.Impl;

import yevhenii.lostfilmdemo.RSS.Feed;
import yevhenii.lostfilmdemo.RSS.RssFeedParser;
import yevhenii.lostfilmdemo.Services.AdaptService;
import yevhenii.lostfilmdemo.Services.FeedService;
import yevhenii.lostfilmdemo.Services.TVSeriesService;

class Service {

    final private String url = "https://www.lostfilm.tv/rss.xml";

    FeedService feedService = new RssFeedParser(url);
    TVSeriesService tvSeriesService = new TVSeriesServiceImpl();
    AdaptService adaptService = new AdaptServiceImpl();

    void start() {
        adaptService.splitFeedMessages(feedService.readFeed());

    }
}
