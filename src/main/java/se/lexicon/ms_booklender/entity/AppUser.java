package se.lexicon.ms_booklender.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import se.lexicon.ms_booklender.exception.DataDuplicateException;
import se.lexicon.ms_booklender.exception.DataNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)

    private String password;
    @CreationTimestamp // by default, it should have now(), and we don't need to write in default constructor
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    //AppUser & Details are completely connected
    // - if we create AppUser, we must create also Details
    // - if we remove AppUser, we must remove also Details
    // -> CascadeType.ALL

    @JoinColumn(name = "details_id")
    private Details details;


    @OneToMany(
            mappedBy = "borrower",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    private List<BookLoan> bookLoans = new ArrayList<>();


/*
    public AppUser() {
        this.regDate=LocalDate.now(); //-> and remove the @NoArgsConstructor
    }



 */

    public AppUser(String username, String password, Details details) {
        this.username = username;
        this.password = password;
        this.details  = details;

    }

    // helper methods for BookLoan:
    public boolean addBookLoan(BookLoan bookLoan){
        if (bookLoans.contains(bookLoan)){
            return false; // it is already added -> we can not loan again
        }
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
        return true;

    }
    public void addBookLoanVoid(BookLoan bookLoan) throws DataDuplicateException
    {
        if (bookLoans.contains(bookLoan)) throw new DataDuplicateException("Book is already in the list");
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public boolean removeBookLoan(BookLoan bookLoan){

        if (!bookLoans.contains(bookLoan)){
            return false; // it is not in the list  -> we can not remove
        }
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
        return true;

    }

    public void removeBookLoanVoid(BookLoan bookLoan)throws DataNotFoundException{

        if (!bookLoans.contains(bookLoan))throw new DataNotFoundException("Book was not in the list");
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);

    }
    /*
     -> we can remove all the code that it is repeated :
     constructors (many types), getters & setters, equals & hashcode, toString
     -> with a LOMBOK INTERFACE ->  in front of the class

        @Setter
        @Getter
        @EqualsAndHashCode
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor

        of course, we can have inside the class a custom constructor too


    public AppUser() {
    }

    public AppUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, regDate);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                '}';
    }

     */
}
