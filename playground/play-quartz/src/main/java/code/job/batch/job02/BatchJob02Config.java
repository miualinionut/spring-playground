package code.job.batch.job02;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJob02Config {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step batchjob02step1() {
        return stepBuilderFactory.get("batch-job02-step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("batch-job02-step1 running");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job batchjob02() {
        return jobBuilderFactory.get("batch-job02")
                .start(batchjob02step1())
                .build();
    }
}
