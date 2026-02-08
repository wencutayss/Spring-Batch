package wn.batch.base_proj.batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookTasklet implements Tasklet{

    int counter = 0;

    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("BookTasklet is Running");
        if(counter ==5){
            counter =0 ;
            return RepeatStatus.FINISHED;
        }
        else{
            counter++ ;
            return RepeatStatus.CONTINUABLE;
        }
    }
    
}
