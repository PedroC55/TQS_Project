package tqs.project.mailMoverPlatform.RepositoriesTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.project.mailMoverPlatform.Repositories.OrdersRepository;

public class OrdersRepositoryTest {
    
    @Autowired
    private OrdersRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void givenAnOrder_whenFindById_thenReturnOrder(){

    }

    @Test
    void givenAnOrder_whenFindByInvalidId_thenReturnNull(){

    }

    @Test
    void givenThreeOrders_whenFindAll_thenReturnThree(){

    }

    @Test
    void givenNoOrders_whenFindAll_thenReturnEmpty(){
        
    }

    @Test
    void givenAnOrder_whenFindByTrackingNumber_thenReturnOrder(){

    }
    @Test
    void givenAnOrder_whenFindByInvalidTrackingNumber_thenReturnNull(){

    }

    @Test
    void givenAnOrder_whenFindByAcpId_thenReturnOrder(){

    }
    @Test
    void givenAnOrder_whenFindByInvalidAcpId_thenReturnNull(){

    }



}
