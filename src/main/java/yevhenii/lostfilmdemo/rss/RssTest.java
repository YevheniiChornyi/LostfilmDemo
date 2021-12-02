package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

@Component
@Log4j2
@RequiredArgsConstructor
public class RssTest implements ApplicationRunner {

    private final FeedService feedService;
    private final TVSeriesConvertor convertService;
    private final TVSeriesService seriesService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("starting rss test");
        String url = "https://www.lostfilm.tv/rss.xml";
        //TODO integrating test
        seriesService.findAll().forEach(System.out::println);
        for (FeedMessage fs :
                feedService.readFeed(url)) {
            log.info(seriesService.save(convertService.convert(fs)));
        }
        log.info("testing db");
        seriesService.findAll().forEach(System.out::println);
        log.info("delete test");
        seriesService.findAll()
                .stream().skip(10)
                .forEach(a -> seriesService.delete(a.getLink()));
        seriesService.findAll().forEach(System.out::println);
        log.info("update test");
        seriesService.findAll().stream().skip(5)
                .peek(a -> a.setLastUpdate("0 0 0 0"))
                .forEach(seriesService::save);
        seriesService.findAll().forEach(System.out::println);
        log.info("ending rss test");
    }
}

