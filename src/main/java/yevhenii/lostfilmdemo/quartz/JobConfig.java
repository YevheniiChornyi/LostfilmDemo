package yevhenii.lostfilmdemo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Slf4j
@Configuration
public class JobConfig {

    @Bean
    public JobDetail getJobDetail() {
        log.info("creating job");
        return JobBuilder.newJob().ofType(LostfilmRssJob.class)
                .storeDurably()
                .withIdentity(new JobKey("lostfilm_job", Scheduler.DEFAULT_GROUP))
                .withDescription("parsing lostfilm url and saving it to db")
                .build();
    }

    @Bean
    public Trigger getTrigger(JobDetail job) {
        log.info("creating trigger");
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(new TriggerKey("lostfilmTrigger", Scheduler.DEFAULT_GROUP))
                .withDescription("checking lostfilm url every n time")
                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(20)
                        .withIntervalInHours(1)
                        .repeatForever())
                .startNow()
                .build();
    }
}
