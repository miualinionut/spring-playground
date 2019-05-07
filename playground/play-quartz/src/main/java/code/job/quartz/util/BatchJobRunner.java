package code.job.quartz.util;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchJobRunner {

    @Autowired
    private JobLauncher jobLauncher;

    public void run(Job batchJob) {
        try {
            runBatchJob(batchJob, 3);
        } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }
    }

    private void runBatchJob(Job batchJob, int retry) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (retry > 0) {
            JobExecution batchJobExecution = jobLauncher.run(batchJob, new JobParameters());
            waitForBatchJobToFinishExecution(batchJobExecution);
            restartQuartzJobInCaseOfException(batchJobExecution, batchJob, retry);
        }
    }

    private void waitForBatchJobToFinishExecution(JobExecution batchJobExecution) {
        while (batchJobExecution.isRunning()) {
            sleep(1 * 1_000); // 1 sec
        }
    }

    private void restartQuartzJobInCaseOfException(JobExecution batchJobExecution, Job batchJob, int retry) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        List<Throwable> exceptions = batchJobExecution.getAllFailureExceptions();
        if (!exceptions.isEmpty()) {
            Throwable e = exceptions.get(0);
            logErrorMessage(batchJob.getName(), e);
            runBatchJob(batchJob, --retry);
        }
    }

    private void logErrorMessage(String jobName, Throwable e) {
        System.out.println("### ERROR: when running batch job " + jobName + " - " + e.getMessage());
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
