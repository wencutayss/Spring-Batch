package wn.batch.base_proj.config;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulearConfig {
    
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired 
    private Job job;
    
    @Scheduled(fixedDelay = 5000L, initialDelay = 2000L)
    public void scheduleJob() throws Exception{
        log.info("Scheduling job Started to work");

        jobLauncher.run(job, new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters());

        log.info("Scheduling job End to work");

    }
}
