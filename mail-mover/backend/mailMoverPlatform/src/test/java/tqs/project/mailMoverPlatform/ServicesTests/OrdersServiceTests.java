package tqs.project.mailMoverPlatform.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.repositories.OrderRepository;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTests {

    @Mock (lenient = true)
    private OrderRepository repository;

    @InjectMocks private OrderServiceImpl service;

    @Test
    void whenAddValidOrder_thenReturnOrderWithValidId(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);
        Mockito.when(service.addOrder(order)).thenReturn(order);
    }
    
    @Test
    void whenGetAllOrders_thenReturnListOfOrders(){

    }

    @Test
    void whenGetByValidAcp_returnListOfOrdersFromAcp(){

    }
    @Test
    void whenGetByInvalidAcp_returnNull(){

    }
    @Test
    void whenGetByValidTrackingNumber_returnOrderWithTrackingNumber(){
        
    }
    @Test
    void whenGetByInvalidTrackingNumber_returnNull(){
        
    }
    @Test
    void whenchangeState_STORE_to_COURIER_thenReturnTrueAndStateIsCOURIER(){
        
    }

    @Test
    void whenchangeState_ACPOINT_to_COLLECTED_thenReturnTrueAndStateIsCOLLECTED(){
        
    }





}
