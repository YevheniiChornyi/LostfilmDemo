package yevhenii.lostfilmdemo.services;

import yevhenii.lostfilmdemo.entity.FeedMessage;

import java.util.List;

public interface FeedService {

    List<FeedMessage> readFeed(String url);

}
