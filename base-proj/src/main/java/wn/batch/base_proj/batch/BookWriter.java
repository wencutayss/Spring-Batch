package wn.batch.base_proj.batch;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import wn.batch.base_proj.entity.BookEntity;
import wn.batch.base_proj.repository.BookRepository;

@Slf4j
public class BookWriter implements ItemWriter<BookEntity>{

    @Autowired
    BookRepository bookRepository;

    @Override
    public void write(Chunk<? extends BookEntity> chunk) throws Exception {
        log.info("Writting {} book", chunk.getItems().size());
        bookRepository.saveAll(chunk.getItems());
    }

}
