package code.quartz.job.job01;

import code.service.HeavyWorkService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuartzJob01 implements Job {

    @Autowired
    private HeavyWorkService heavyWorkService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(this.getClass().getSimpleName() + " is running");
        heavyWorkService.work(this.getClass().getSimpleName());
    }

}