package wn.batch.base_proj.config;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.infrastructure.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import wn.batch.base_proj.batch.BookAuthorProcessor;
import wn.batch.base_proj.batch.BookTasklet;
import wn.batch.base_proj.batch.BookTitleProcessor;
import wn.batch.base_proj.batch.BookWriter;
import wn.batch.base_proj.batch.RestBookReader;
import wn.batch.base_proj.entity.BookEntity;


@Configuration
@Slf4j
public class BatchConfig {

    @Bean
    public Job bookReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("bookReaderJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(chunkStep(jobRepository, transactionManager))
        .next(taskletStep(jobRepository, transactionManager))
        .build();
    }

    private Step taskletStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {
                return new StepBuilder("taskletStep", jobRepository)
                .tasklet(new BookTasklet(), transactionManager)
                .build();
       
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("stepReaderJob", jobRepository)
                    .<BookEntity, BookEntity>chunk(10, transactionManager)
                    // .reader(reader())
                    .reader(restBookReader())
                    .processor(processor())
                    .writer(writer())
                    .build();
    }

     @Bean 
    @StepScope
    public ItemReader<BookEntity> restBookReader(){
        return new RestBookReader("http://localhost:8080/books", new RestTemplate());
    }

    @Bean 
    @StepScope
    public ItemWriter<BookEntity> writer() {
        return new BookWriter();
        
    }

    @Bean 
    @StepScope
    public ItemProcessor<BookEntity, BookEntity> processor() {
        CompositeItemProcessor<BookEntity, BookEntity> processor = new CompositeItemProcessor<>();
        processor.setDelegates(List.of(new BookTitleProcessor(), new BookAuthorProcessor()));

        return processor;
        
    }

    @Bean 
    @StepScope
    public FlatFileItemReader<BookEntity> reader() {
        return new FlatFileItemReaderBuilder<BookEntity>()
                .name("bookReader")
                .resource(new ClassPathResource("books_data.csv"))
                .delimited()
                .names(new String[]{"title", "author", "publishedYear"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{setTargetType(BookEntity.class);}})
                .linesToSkip(1)
                .build();
        
    }
    
}
