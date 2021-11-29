package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.services.FeedService;

@Component
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RssTest implements ApplicationRunner {

    private final FeedService feedService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("starting rss");
        String url = "https://www.lostfilm.tv/rss.xml";

        Converter<FeedMessage, TVSeries> convertService = new TVSeriesConvertor();
        for (FeedMessage fs :
                feedService.readFeed(url)) {
            log.info(convertService.convert(fs));
        }
    }
}

