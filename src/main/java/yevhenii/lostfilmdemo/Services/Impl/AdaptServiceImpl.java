package yevhenii.lostfilmdemo.Services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yevhenii.lostfilmdemo.Entity.TVSeries;
import yevhenii.lostfilmdemo.RSS.Feed;
import yevhenii.lostfilmdemo.RSS.FeedMessage;
import yevhenii.lostfilmdemo.Services.AdaptService;
import yevhenii.lostfilmdemo.Services.TVSeriesService;

class AdaptServiceImpl implements AdaptService {

    @Override
    public void splitFeedMessages(Feed feed) {

        for (FeedMessage feedMessage :
                feed.getMessages()) {
            adapt(feedMessage);
        }
    }

    @Override
    public TVSeries adapt(FeedMessage feedMessage) {
        final String name;
        final String russianName;
        String image;
        int season;
        int episode;
        String link = feedMessage.getLink();


        return null;
    }
}
