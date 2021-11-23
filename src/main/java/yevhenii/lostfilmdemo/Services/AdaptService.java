package yevhenii.lostfilmdemo.Services;

import yevhenii.lostfilmdemo.Entity.TVSeries;
import yevhenii.lostfilmdemo.RSS.Feed;
import yevhenii.lostfilmdemo.RSS.FeedMessage;

public interface AdaptService {

    void splitFeedMessages(Feed feed);

    TVSeries adapt(FeedMessage feedMessage);
}
