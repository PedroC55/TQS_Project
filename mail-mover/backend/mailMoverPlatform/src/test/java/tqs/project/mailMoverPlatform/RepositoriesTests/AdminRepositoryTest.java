package tqs.project.mailMoverPlatform.repositoriesTests;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.repositories.AdminRepository;


@DataJpaTest
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository repository;
    
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenAnAdmin_whenFindById_thenReturnAdmin(){
        Admin admin = new Admin("admin@mail.com", "pw_admin");
        entityManager.persistAndFlush(admin);
        
        Optional<Admin> response = repository.findById(admin.getId());
        System.out.println(response.toString());

        assertThat(response).isPresent().contains(admin);
    }

    @Test
    void givenAnAdmin_whenFindByInvalidId_thenReturnNull(){
        //Optional<Admin> response = repository.findById("invalidID");
        //assertThat(response).isNotPresent();
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
