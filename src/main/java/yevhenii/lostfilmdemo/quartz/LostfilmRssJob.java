package yevhenii.lostfilmdemo.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

@Log4j2
@Component
@RequiredArgsConstructor//(onConstructor = @__(@Autowired))
public class LostfilmRssJob implements Job {

    private final FeedService feedService;
    private final TVSeriesConvertor convertService;
    private final TVSeriesService seriesService;
    @Value("${tvSeries.url:https://www.lostfilm.tv/rss.xml}")
    String url;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("on execute");
        feedService.readFeed(url)
                .forEach(fs -> seriesService.save(convertService.convert(fs)));
        log.info("end executing");
    }


}
