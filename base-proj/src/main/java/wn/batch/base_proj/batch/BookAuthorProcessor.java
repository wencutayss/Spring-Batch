package wn.batch.base_proj.batch;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;
import wn.batch.base_proj.entity.BookEntity;

@Slf4j
public class BookAuthorProcessor implements ItemProcessor<BookEntity, BookEntity> {

    @Override
    public @Nullable BookEntity process(BookEntity item) throws Exception {
        log.info("Processing the Author for {}", item);
        item.setAuthor("By "+ item.getAuthor());
        return item;
    }
    
}
