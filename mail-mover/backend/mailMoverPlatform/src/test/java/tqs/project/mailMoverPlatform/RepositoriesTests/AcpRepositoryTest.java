package tqs.project.mailMoverPlatform.RepositoriesTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.project.mailMoverPlatform.Entities.ACP;
import tqs.project.mailMoverPlatform.Repositories.AcpRepository;

public class AcpRepositoryTest {
    @Autowired
    private AcpRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenAnAcp_whenFindById_thenReturnAcp(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        entityManager.persistAndFlush(acp);
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
