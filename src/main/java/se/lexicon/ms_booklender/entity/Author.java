package se.lexicon.ms_booklender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.ms_booklender.exception.DataDuplicateException;
import se.lexicon.ms_booklender.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @ManyToMany (cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name= "books_authors",
    joinColumns = @JoinColumn(name = "author_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> writtenBooks = new ArrayList<>();

    public void addBook(Book book){
        if(writtenBooks.contains(book)) throw new DataDuplicateException("Data Duplicate Exception");
        writtenBooks.add(book);

        // we used JoinTable -> both tables use the same mapped table -> we don't need to the other side
    }
    public void removeBook(Book book){
        if(!writtenBooks.contains(book)) throw new DataNotFoundException("Data Not found Exception");
    }
}
