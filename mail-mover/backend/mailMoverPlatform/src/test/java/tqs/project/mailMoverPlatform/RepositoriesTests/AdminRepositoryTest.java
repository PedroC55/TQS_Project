package tqs.project.mailMoverPlatform.repositoriesTests;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.repositories.AdminRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        assertThat(response).isPresent().contains(admin);
    }

    @Test
    void givenAnAdmin_whenFindByInvalidId_thenReturnNull(){
        Optional<Admin> response = repository.findById(-1L);
        assertThat(response).isNotPresent();
    }

    @Test
    void givenTwoAdmins_whenFindAll_thenReturnTwo(){
        Admin admin1 = new Admin("admin1@mail.com", "pw_admin1");
        Admin admin2 = new Admin("admin2@mail.com", "pw_admin2");
    
        entityManager.persistAndFlush(admin1);
        entityManager.persistAndFlush(admin2);

        List<Admin> allAdmins = repository.findAll();
        assertThat(allAdmins).isNotNull();
        assertThat(allAdmins)
            .hasSize(2)
            .extracting(Admin::getEmail)
            .contains(admin1.getEmail(), admin2.getEmail());
    }

    @Test
    void givenNoAdmins_whenFindAll_thenReturnEmpty(){
        List<Admin> allAdmins = repository.findAll();
        assertThat(allAdmins).isNotNull().isEmpty();
    }

    @Test
    void givenAnAdmin_whenFindByEmail_thenReturnAdmin(){
        Admin admin = new Admin("admin@mail.com", "pw_admin");
        entityManager.persistAndFlush(admin);
        
        Optional<Admin> response = repository.findByEmail(admin.getEmail());
        assertThat(response.get().getEmail().equals(admin.getEmail()));

    }
    @Test
    void givenAnAdmin_whenFindByInvalidEmail_thenReturnNull(){
        Optional<Admin> response = repository.findByEmail("fakeEmail@mail.com");
        assertThat(response).isNotPresent();
    }

}
