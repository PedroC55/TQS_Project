package tqs.project.mailMoverPlatform.repositoriesTests;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

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
        Optional<ACP> response = repository.findById(-1L);
        assertThat(response).isNotPresent();
    }

    @Test
    void givenFiveAcp_whenFindAll_thenReturnFive(){
        ACP acp1 = new ACP("Loja ACP 1","Rua dos correios","lojaAcp1@mail.com","pw_acp1");
        ACP acp2 = new ACP("Loja ACP 2","Avenida da Liberdade","lojaAcp2@mail.com","pw_acp2");
        ACP acp3 = new ACP("Loja ACP 3","Rua Mário Sacramento","lojaAcp3@mail.com","pw_acp3");
        ACP acp4 = new ACP("Loja ACP 4","Rua da Saudade","lojaAcp4@mail.com","pw_acp4");
        ACP acp5 = new ACP("Loja ACP 5","Rua Dom Infante","lojaAcp5@mail.com","pw_acp5");
        
        entityManager.persistAndFlush(acp1);
        entityManager.persistAndFlush(acp2);
        entityManager.persistAndFlush(acp3);
        entityManager.persistAndFlush(acp4);
        entityManager.persistAndFlush(acp5);

        List<ACP> allACPs = repository.findAll();

        assertThat(allACPs).isNotNull();
        assertThat(allACPs)
            .hasSize(5)
            .extracting(ACP::getName)
            .contains(acp1.getName(), acp2.getName(), acp3.getName(), acp4.getName(), acp5.getName());
    }

    @Test
    void givenNoAcp_whenFindAll_thenReturnEmpty(){
        List<ACP> allACPs = repository.findAll();
        assertThat(allACPs).isNotNull().isEmpty();
    }
    
}
