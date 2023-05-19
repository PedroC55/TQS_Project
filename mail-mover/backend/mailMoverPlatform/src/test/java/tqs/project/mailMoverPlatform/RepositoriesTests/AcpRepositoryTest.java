package tqs.project.mailMoverPlatform.repositoriesTests;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;

@DataJpaTest
public class AcpRepositoryTest {
    @Autowired
    private AcpRepository repository;
    
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenAnAcp_whenFindById_thenReturnAcp(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        entityManager.persistAndFlush(acp);

        Optional<ACP> response = repository.findById(acp.getId());

        assertThat(response).isPresent().contains(acp);
    }

    @Test
    void givenAnAcp_whenFindByInvalidId_thenReturnNull(){

    }

    @Test
    void givenFiveAcp_whenFindAll_thenReturnFive(){

    }

    @Test
    void givenNoAcp_whenFindAll_thenReturnEmpty(){
        
    }
    
}
