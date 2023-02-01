package se.lexicon.ms_booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ms_booklender.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {


    List<Book> findAllByIsbn(String isbn);
    List<Book> findAllByTitleContains(String title);
    List<Book> findAllByTitleContainsIgnoreCase(String title);

}
