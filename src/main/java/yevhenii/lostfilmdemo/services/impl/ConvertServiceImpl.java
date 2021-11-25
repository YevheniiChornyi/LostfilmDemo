package yevhenii.lostfilmdemo.services.impl;

import org.springframework.stereotype.Service;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.rss.Feed;
import yevhenii.lostfilmdemo.rss.FeedMessage;
import yevhenii.lostfilmdemo.services.ConvertService;

@Service
class ConvertServiceImpl implements ConvertService {

    public void splitFeedMessages(Feed feed) {

//        for (FeedMessage feedMessage :
//                feed.getMessages()) {
//            convert(feedMessage);
//        }
    }

    @Override
    public TVSeries convert(FeedMessage feedMessage) {
        final String name;
        final String russianName;
        String image;
        int season;
        int episode;
        String link = feedMessage.getLink();


        return null;
    }
}
