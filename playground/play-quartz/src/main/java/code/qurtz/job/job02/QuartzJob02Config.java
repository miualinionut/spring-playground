package code.qurtz.job.job02;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzJob02Config {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void schedule() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob().ofType(QuartzJob02.class)
                .storeDurably()
                .withIdentity("QuartzJob02_Detail")
                .withDescription("Invoke QuartzJob02 ...")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("QuartzJob02_Trigger")
                .withDescription("QuartzJob02 trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}