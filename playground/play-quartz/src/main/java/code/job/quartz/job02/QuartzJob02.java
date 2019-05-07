package code.job.quartz.job02;

import code.job.quartz.util.BatchJobRunner;
import code.service.HeavyWorkService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QuartzJob02 implements org.quartz.Job {

    @Autowired
    private HeavyWorkService heavyWorkService;
    @Autowired
    private BatchJobRunner batchJobRunner;
    @Autowired
    @Qualifier("batchjob02")
    private Job batchJob;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("--------------");
        System.out.println(this.getClass().getSimpleName() + " is running");
        heavyWorkService.work(this.getClass().getSimpleName());
        batchJobRunner.run(batchJob);
    }

}