package se.lexicon.ms_booklender.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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

/*
    public AppUser() {
        this.regDate=LocalDate.now(); //-> and remove the @NoArgsConstructor
    }

 */

    public AppUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
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
