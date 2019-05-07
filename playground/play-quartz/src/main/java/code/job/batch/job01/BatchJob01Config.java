package code.job.batch.job01;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJob01Config {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step batchjob01step01() {
        return stepBuilderFactory.get("batch-job01-step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("batch-job01-step1 running");
                    //throw new RuntimeException("oops");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job batchjob01() {
        return jobBuilderFactory.get("batch-job01")
                .start(batchjob01step01())
                .build();
    }
}
