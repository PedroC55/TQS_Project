package tqs.project.mailMoverPlatform.repositoriesTests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.repositories.OrderRepository;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED) 
public class OrdersRepositoryTest {
    
    @Autowired
    private OrderRepository repository;
    @Autowired
    private AcpRepository acp_repository;
    @BeforeEach
    void setUp(){
        repository.deleteAll();
        acp_repository.deleteAll();
    }

    @Test
    void givenAnOrder_whenFindById_thenReturnOrder(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        //entityManager.persistAndFlush(acp);
        acp_repository.save(acp);
        Order order = new Order("Cliente 1", acp);
        //entityManager.persistAndFlush(order);
        repository.save(order);
        Optional<Order> response = repository.findById(order.getId());
        assertThat(response).isPresent();
        assertThat(response.get().getAcp().equals(acp));
    }

    @Test
    void givenAnOrder_whenFindByInvalidId_thenReturnNull(){
        Optional<Order> response = repository.findById(-1L);
        assertThat(response).isNotPresent();
    }

    @Test
    void givenThreeOrders_whenFindAll_thenReturnThree(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp_repository.save(acp);
        
        Order order1 = new Order("Cliente 1", acp);
        Order order2 = new Order("Cliente 2", acp);
        Order order3 = new Order("Cliente 3", acp);
        
        repository.save(order1);
        repository.save(order2);
        repository.save(order3);

        List<Order> allOrders = repository.findAll();
        
        assertThat(allOrders).isNotNull();
        assertThat(allOrders)
            .hasSize(3)
            .extracting(Order::getclientName)
            .contains(order1.getclientName(), order2.getclientName(), order3.getclientName());
    }

    @Test
    void givenNoOrders_whenFindAll_thenReturnEmpty(){
        List<Order> allOrders = repository.findAll();
        assertThat(allOrders).isNotNull().isEmpty();
    }


    @Test
    void givenAnOrder_whenFindByAcp_thenReturnOrder(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp_repository.save(acp);
        Order order = new Order("Cliente 1", acp);
        repository.save(order);

        List<Order> response = repository.findByAcp(acp);
        assertThat(response).isNotNull();
        assertThat(response)
            .hasSize(1)
            .extracting(Order::getclientName)
            .contains(order.getclientName());
    }
    @Test
    void givenAnOrder_whenFindByInvalidAcp_thenReturnNull(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        ACP fake_acp = new ACP("Loja ACP fake","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp_repository.save(acp);
        acp_repository.save(fake_acp);
        Order order = new Order("Cliente 1", acp);
        repository.save(order);

        List<Order> response = repository.findByAcp(fake_acp);
        assertThat(response).isEmpty();
    }
    @Test
    void givenAnOrder_whenFindByNonexistentAcp_thenReturnException(){
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        ACP fake_acp = new ACP("Loja ACP fake","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp_repository.save(acp);

        Order order = new Order("Cliente 1", acp);
        repository.save(order);

        assertThrows(Exception.class, () -> {repository.findByAcp(fake_acp);});
       
    }



}
