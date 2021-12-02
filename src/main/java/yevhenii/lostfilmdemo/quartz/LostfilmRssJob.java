package yevhenii.lostfilmdemo.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

@RequiredArgsConstructor
public class LostfilmRssJob implements Job {


    private final FeedService feedService;
    private final TVSeriesConvertor convertService;
    private final TVSeriesService seriesService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String url = "https://www.lostfilm.tv/rss.xml";
        for (FeedMessage fs :
                feedService.readFeed(url)) {
            seriesService.save(convertService.convert(fs));
        }
    }
}
