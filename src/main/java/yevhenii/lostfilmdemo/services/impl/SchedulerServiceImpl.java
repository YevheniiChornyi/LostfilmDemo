package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import yevhenii.lostfilmdemo.services.SchedulerService;

@Log4j2
@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private final Scheduler scheduler;

    public void start() throws SchedulerException {

        scheduler.start();
    }

}
