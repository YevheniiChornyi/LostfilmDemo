package yevhenii.lostfilmdemo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;

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
    public Trigger getTrigger(JobDetail job, @Value("${spring.quartz.cron.default}") String cron) {
        log.info("creating cron trigger");
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(new TriggerKey("lostfilmCronTrigger", Scheduler.DEFAULT_GROUP))
                .withDescription("checking lostfilm url every cron time")
                .withSchedule(cronSchedule(cron).withMisfireHandlingInstructionFireAndProceed())
                .startNow()
                .build();
    }

}
