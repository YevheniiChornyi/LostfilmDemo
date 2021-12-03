package yevhenii.lostfilmdemo.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.services.FeedService;
import yevhenii.lostfilmdemo.services.TVSeriesService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LostfilmRssJob implements Job {

    private final FeedService feedService;
    private final TVSeriesConvertor convertService;
    private final TVSeriesService seriesService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("on execute");
        feedService.readFeed(getUrl())
                .forEach(fs -> seriesService.save(convertService.convert(fs)));
        log.info("end executing");
    }

    private String getUrl(){
        String propertyFilePath = "config.properties";
        try (InputStream input = new FileInputStream(propertyFilePath)){
            final Properties properties = new Properties();
                    properties.load(input);
        return properties.getProperty("tvSeries.url");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("cant find properties file");
            return "https://www.lostfilm.tv/rss.xml";
        }
    }
}
