package se.lexicon.ms_booklender.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.ms_booklender.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository // it is already a bean -> it is not mandatory to add @Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    // all methods exist in CrudRepository :
    // save(create/persist), findById, update, delete...

    // to define another method that does not exist in CrudRepository

    //-> SELECT * FROM app_user WHERE username = 1?
    Optional<AppUser> findByUsername (String username);

    //method is already implemented if we follow the SpringData naming convention:
    //"Optional<AppUser> findBy" + field +()

    @Query("SELECT a FROM AppUser a WHERE a.username = : un")
    Optional<AppUser> selectByUsername (@Param("un") String username);
    //without following the SpringData naming convention


    //or we can execute direct the query
    //-> SELECT * FROM app_user WHERE reg_date BETWEEN 1? AND 2?

    List<AppUser> findAllByRegDateBetween(LocalDate from, LocalDate to);
    //SpringData naming convention: List<AppUser> findAllBy + field +condition

    @Query("SELECT a FROM AppUser a WHERE a.regDate>= :from AND a.regDate<= :to")
    List<AppUser> selectByRegistrationDate(@Param("from") LocalDate from,@Param("to") LocalDate to);
    //without following the SpringData naming convention


    @Modifying
    @Query("UPDATE AppUser a SET a.password = : pwd WHERE a.username = : un")
    //this query will modify DB -> add @Modifying
    // for ex: SELECT is readonly
    void resetPassword(@Param("un")String username,@Param("pwd") String password);



}
