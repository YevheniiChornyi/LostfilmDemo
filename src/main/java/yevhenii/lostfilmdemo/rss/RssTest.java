package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.services.FeedService;

@Component
@Log4j2
@RequiredArgsConstructor
public class RssTest implements ApplicationRunner {

    private final FeedService feedService;
    private final Converter<FeedMessage, TVSeries> convertService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("starting rss test");
        String url = "https://www.lostfilm.tv/rss.xml";
        //TODO backlog level
        for (FeedMessage fs :
                feedService.readFeed(url)) {
            log.debug(convertService.convert(fs));
        }
        log.debug("ending rss test");
    }
}

