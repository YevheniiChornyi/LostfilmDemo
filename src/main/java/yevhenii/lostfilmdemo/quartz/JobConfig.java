package yevhenii.lostfilmdemo.quartz;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerKey.triggerKey;

@Log4j2
@Configuration
public class JobConfig {
    @Getter
    private static int jobCount = 1;
    @Getter
    private static int triggerCount = 1;

    @Bean
    public JobDetail getJobDetail() {
        log.info("creating job");
        return JobBuilder.newJob(LostfilmRssJob.class)
                .storeDurably()
                .withIdentity("lostfilm_job" + jobCount++, "lostfilmGroup")
                .withDescription("parsing lostfilm url and saving it to db")
                .build();
    }
    @Bean
    public Trigger getTrigger(JobDetail job) {
        log.info("creating trigger");
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(triggerKey("lostfilmTrigger" + triggerCount++, "lostfilmGroup"))
                .withDescription("checking lostfilm url every hour")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(20)
                        .repeatForever())
                .startNow()
                .build();
    }
}
