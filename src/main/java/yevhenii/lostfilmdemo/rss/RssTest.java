package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.services.SchedulerService;

@Component
@Log4j2
@RequiredArgsConstructor
public class RssTest implements ApplicationRunner {

    private final SchedulerService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("starting rss test");
        //TODO integrating test
        service.start();

    }
}

