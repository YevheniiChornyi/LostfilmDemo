package yevhenii.lostfilmdemo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication(scanBasePackages = {"yevhenii.lostfilmdemo"})
public class LostfilmDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        log.info("started");
        SpringApplication.run(LostfilmDemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        log.info("starting");
//        String url = "https://www.lostfilm.tv/rss.xml";
//
//        FeedService feedService = new FeedServiceImpl(new FeedMessageConvertor());
//
//        Converter<FeedMessage, TVSeries> convertService = new TVSeriesConvertor();
//        // System.out.println(feedService.readFeed(url));
//        for (FeedMessage fs :
//                feedService.readFeed(url)) {
////            System.out.println(convertService.convert(fs));
//            log.info(convertService.convert(fs));
//        }
    }
}
