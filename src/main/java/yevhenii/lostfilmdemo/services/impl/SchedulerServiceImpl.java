package yevhenii.lostfilmdemo.services.impl;

import lombok.Getter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import yevhenii.lostfilmdemo.quartz.LostfilmRssJob;
import yevhenii.lostfilmdemo.services.SchedulerService;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerKey.triggerKey;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    @Getter
    static int jobCount;
    @Getter
    static int triggerCount;

    public void start() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(this.getTrigger(getJobDetail()));//Exception
        scheduler.start();
    }


    private JobDetail getJobDetail() {
        return JobBuilder.newJob(LostfilmRssJob.class)
                .withIdentity("lostfilm_job" + jobCount++, "lostfilmGroup")
                .withDescription("parsing lostfilm url and saving it to db")
                .build();

    }

    private Trigger getTrigger(JobDetail job) {
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
