package wn.batch.base_proj.batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;
import wn.batch.base_proj.entity.BookEntity;

@Slf4j
public class BookTitleProcessor implements ItemProcessor<BookEntity, BookEntity>{
    
    @Override
    public @Nullable BookEntity process(BookEntity item) throws Exception {
        log.info("Processing title {}", item);

        item.setTitle(item.getTitle().toUpperCase());

        return item;
    }

    
    
}
