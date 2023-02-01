package se.lexicon.ms_booklender.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

//Lombok

@Data // contains @Setter, @Getter, @EqualsAndHashCode, @ToString + RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Details {

    @Id
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    private LocalDate birthdate;


    //custom constructor
    public Details(String email, String name, LocalDate birthdate) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
    }
}
