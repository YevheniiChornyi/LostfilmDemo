package yevhenii.lostfilmdemo.rss;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.convertors.FeedMessageConvertor;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.impl.FeedServiceImpl;

//TODO use Spring utils for that, add controller to use ApplicationRunner
@Component
@Log4j2
public class RssTest implements ApplicationRunner  {
//    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
////        String url = "https://www.lostfilm.tv/rss.xml";
////
////        FeedService feedService = new FeedServiceImpl(new FeedMessageConvertor());
////
////        Converter<FeedMessage, TVSeries> convertService = new TVSeriesConvertor();
////        // System.out.println(feedService.readFeed(url));
////        for (FeedMessage fs :
////                feedService.readFeed(url)) {
////            System.out.println(convertService.convert(fs));
////        }
//
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("starting rss");
        String url = "https://www.lostfilm.tv/rss.xml";

        FeedService feedService = new FeedServiceImpl(new FeedMessageConvertor());

        Converter<FeedMessage, TVSeries> convertService = new TVSeriesConvertor();
        // System.out.println(feedService.readFeed(url));
        for (FeedMessage fs :
                feedService.readFeed(url)) {
//            System.out.println(convertService.convert(fs));
            log.info(convertService.convert(fs));
        }
    }
}

