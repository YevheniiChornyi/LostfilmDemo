package yevhenii.lostfilmdemo.rss;

import org.xml.sax.SAXException;
import yevhenii.lostfilmdemo.services.FeedService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RssTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String url = "https://www.lostfilm.tv/rss.xml";
        FeedService feedService = new DocumentParser();
        System.out.println(feedService.readFeed(url));

    }
}
