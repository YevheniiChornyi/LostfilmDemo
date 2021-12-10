package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.kafka.AlertReader;
import yevhenii.lostfilmdemo.kafka.AlertSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class RssRunner implements ApplicationRunner {

    private final AlertReader alertReader;
    private final AlertSender alertSender;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("starting rss test");
        alertSender.send("lostfilmTvSeries", "hello");
    }
}

