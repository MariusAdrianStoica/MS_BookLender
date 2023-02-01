package se.lexicon.ms_booklender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Book book;

    public BookLoan(LocalDate loanDate, AppUser borrower, Book book) {
        this.dueDate =  loanDate.plusDays(book.getMaxLoanDays());
        // we can calculate directly the dueDate
        this.loanDate = loanDate;
        this.borrower = borrower;
        this.book = book;
        setBorrower(borrower);
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
        // if the borrower exists -> returned false
        this.returned = (borrower == null);
    }
}
