package tqs.project.mailMoverPlatform.repositoriesTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.project.mailMoverPlatform.repositories.AdminRepository;

public class AdminRepositoryTest {
    @Autowired
    private AdminRepository repository;
    
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenAnAdmin_whenFindById_thenReturnAdmin(){

    }

    @Test
    void givenAnAdmin_whenFindByInvalidId_thenReturnNull(){

    }

    @Test
    void givenTwoAdmins_whenFindAll_thenReturnTwo(){

    }

    @Test
    void givenNoAdmins_whenFindAll_thenReturnEmpty(){
        
    }

    @Test
    void givenAnAdmin_whenFindByEmail_thenReturnAdmin(){

    }
    @Test
    void givenAnAdmin_whenFindByInvalidEmail_thenReturnNull(){

    }

}
