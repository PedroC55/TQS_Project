package tqs.project.mailMoverPlatform.ServicesTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tqs.project.mailMoverPlatform.Repositories.OrdersRepository;
import tqs.project.mailMoverPlatform.Services.OrdersService;

public class OrdersServiceTests {

    @Mock
    private OrdersRepository repository;
    @InjectMocks
    private OrdersService service;

    @Test
    void whenAddValidOrder_thenReturnOrderWithValidId(){
    
    }
    
    @Test
    void whenGetAllOrders_thenReturnListOfOrders(){

    }

}
