package se.lexicon.ms_booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ms_booklender.entity.Book;
import se.lexicon.ms_booklender.entity.BookLoan;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {



}
