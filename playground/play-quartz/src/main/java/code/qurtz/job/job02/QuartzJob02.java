package code.qurtz.job.job02;

import code.service.HeavyWorkService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuartzJob02 implements Job {

    @Autowired
    private HeavyWorkService heavyWorkService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("QuartzJob02 running");
        heavyWorkService.work(this.getClass().getSimpleName());
    }

}