package yevhenii.lostfilmdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yevhenii.lostfilmdemo.repository.TVSeriesRepository;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

@SpringBootTest
class LostfilmDemoApplicationTests {

    @Autowired
    FeedService feedService;
    @Autowired
    TVSeriesService service;
    @Autowired
    TVSeriesRepository repository;

    @Test
    void contextLoads() {

    }

}
