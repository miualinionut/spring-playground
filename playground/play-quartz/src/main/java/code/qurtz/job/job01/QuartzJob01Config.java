package code.qurtz.job.job01;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzJob01Config {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void schedule() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob().ofType(QuartzJob01.class)
                .storeDurably()
                .withIdentity("QuartzJob01_Detail")
                .withDescription("Invoke QuartzJob01 ...")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("QuartzJob01_Trigger")
                .withDescription("QuartzJob01 trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}