package yevhenii.lostfilmdemo.services;

import org.xml.sax.SAXException;
import yevhenii.lostfilmdemo.rss.Feed;
import yevhenii.lostfilmdemo.rss.FeedMessage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface FeedService {

    List<FeedMessage> readFeed(String url) throws ParserConfigurationException, IOException, SAXException;

}
