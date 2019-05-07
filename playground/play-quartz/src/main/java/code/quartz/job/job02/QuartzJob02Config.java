package code.quartz.job.job02;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzJob02Config {

    @Autowired
    private Scheduler scheduler;
    private final Class JOB_CLASS = QuartzJob02.class;

    @Bean
    public JobDetail job02() {
        return JobBuilder.newJob().ofType(JOB_CLASS)
                .storeDurably()
                .withIdentity(JOB_CLASS.getSimpleName() + "_Detail")
                .withDescription(JOB_CLASS.getSimpleName() + "_Description")
                .build();
    }

    @Bean
    public Trigger job02Trigger(@Qualifier("job02") JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity(JOB_CLASS.getSimpleName() + "_Detail_Trigger")
                .withDescription(JOB_CLASS.getSimpleName() + "_Description_Trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(1))
                .build();
    }

    @PostConstruct
    public void schedule() throws SchedulerException {
        scheduler.scheduleJob(job02(), job02Trigger(null));
    }

}