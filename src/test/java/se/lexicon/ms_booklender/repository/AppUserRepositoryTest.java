package se.lexicon.ms_booklender.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.ms_booklender.entity.AppUser;
import se.lexicon.ms_booklender.entity.Details;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {

    // @DataJpaTest - used to test all the methods from CrudRepository

    @Autowired // @Autowired -> AppUserDao is injected in this Test class
    AppUserRepository testObject;

    AppUser createdAppUser;
    @BeforeEach // 1st -> we need to initialize to create 2 object in the DB
    public void setup(){
        Details detailsData = new Details("user@test.se", "Test Test", LocalDate.parse("2000-01-01"));
        AppUser appUserData = new AppUser("user", "pass",detailsData);
        createdAppUser = testObject.save(appUserData);
        assertNotNull(createdAppUser);
    }

    @Test
    public void test_findById(){
        Optional<AppUser> appUserOptional =testObject.findById(createdAppUser.getId());
        assertTrue(appUserOptional.isPresent());

        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser;

        assertEquals(expectedData, actualData);

    }
    @Test
    public void test_findByUsername(){
        Optional<AppUser> appUserOptional =testObject.findByUsername(createdAppUser.getUsername());
        assertTrue(appUserOptional.isPresent());

        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser;

        assertEquals(expectedData, actualData);

    }

    @Test
    public void test_selectByRegistrationDate(){

        //- just a model how to write
        // testObject.selectByRegistrationDate(LocalDate.parse("2020-01-01"), LocalDate.parse("2023-01-01"));
    }
}
