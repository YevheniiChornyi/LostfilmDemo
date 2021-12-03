package yevhenii.lostfilmdemo.services.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Service;
import yevhenii.lostfilmdemo.quartz.LostfilmRssJob;
import yevhenii.lostfilmdemo.services.SchedulerService;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerKey.triggerKey;

@Log4j2
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    @Getter
    static int jobCount;
    @Getter
    static int triggerCount;
    private final Scheduler scheduler;

    public void start() throws SchedulerException {

        Trigger trigger = getTrigger(getJobDetail());
        if (!scheduler.checkExists(trigger.getKey()))
        scheduler.scheduleJob(trigger);

        scheduler.start();
    }

    private JobDetail getJobDetail() {
        log.info("creating job");
        return JobBuilder.newJob(LostfilmRssJob.class)
                .withIdentity("lostfilm_job" + jobCount++, "lostfilmGroup")
                .withDescription("parsing lostfilm url and saving it to db")
                .build();
    }

    private Trigger getTrigger(JobDetail job) {
        log.info("creating trigger");
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity(triggerKey("lostfilmTrigger" + triggerCount++, "lostfilmGroup"))
                .withDescription("checking lostfilm url every hour")
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .startNow()
                .build();
    }
}
