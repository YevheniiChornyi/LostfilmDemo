package yevhenii.lostfilmdemo.rss;

import org.xml.sax.SAXException;
import yevhenii.lostfilmdemo.services.ConvertService;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.impl.ConvertServiceImpl;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RssTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String url = "https://www.lostfilm.tv/rss.xml";
        FeedService feedService = new DocumentParser();
        ConvertService convertService = new ConvertServiceImpl();
       // System.out.println(feedService.readFeed(url));
        for (FeedMessage fs:
             feedService.readFeed(url)) {
            System.out.println(convertService.convert(fs));
        }

    }
}
