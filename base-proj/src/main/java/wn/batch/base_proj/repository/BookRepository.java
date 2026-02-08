package wn.batch.base_proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wn.batch.base_proj.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{
    
}
