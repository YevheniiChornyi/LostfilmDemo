package yevhenii.lostfilmdemo.rss;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.controllers.ImdbHolderClient;
import yevhenii.lostfilmdemo.kafka.AlertSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class RssRunner implements ApplicationRunner {

    private final AlertSender alertSender;
    private final Scheduler scheduled;
    private final ImdbHolderClient client;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("starting rss test");
//        scheduled.pauseTrigger(TriggerKey.triggerKey("lostfilmTrigger"));
//        scheduled.resumeTrigger(TriggerKey.triggerKey("lostfilmTrigger"));
        alertSender.send("lostfilmTvSeries", "hello");
        log.info(client.getSeason("tt0411008",1).toString());
        log.info(client.getSeries("lost 2004").toString());
        log.info("alarm\nalarm\nalarm\nalarm\nalarm\nalarm\nalarm\nalarm\nalarm\nalarm\n");

   }
}

