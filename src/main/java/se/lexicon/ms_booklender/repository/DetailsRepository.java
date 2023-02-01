package se.lexicon.ms_booklender.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.ms_booklender.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Optional<Details> findByEmailIgnoreCase(String email);

    List<Details> findAllByNameContains(String name);

    //select after 2 conditions
    List<Details> findAllByNameIgnoreCaseAndBirthdate(String name, LocalDate birthdate);





}
