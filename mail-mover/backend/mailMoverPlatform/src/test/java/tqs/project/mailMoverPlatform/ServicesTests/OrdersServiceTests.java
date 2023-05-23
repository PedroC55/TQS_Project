package tqs.project.mailMoverPlatform.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
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
        ACP acp1 = new ACP("Loja ACP 1", "Rua dos correios 1", "lojaAcp1@mail.com", "pw_acp");
        Order order1 = new Order("cliente1", acp1);
        ACP acp2 = new ACP("Loja ACP 2", "Rua dos correios 2", "lojaAcp2@mail.com", "pw_acp");
        Order order2 = new Order("cliente2", acp2);
        ACP acp3 = new ACP("Loja ACP 3", "Rua dos correios 3", "lojaAcp3@mail.com", "pw_acp");
        Order order3 = new Order("cliente3", acp3);

        List<Order> orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        Mockito.when(service.getAllOrders()).thenReturn(orders);

        List<Order> returned_list_orders = service.getAllOrders();
        assertThat(returned_list_orders).containsExactly(order1, order2, order3);
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    void whenGetByValidAcp_returnListOfOrdersFromAcp(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order1 = new Order("cliente1", acp);
        Order order2 = new Order("cliente2", acp);
        Order order3 = new Order("cliente3", acp);

        List<Order> orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        Mockito.when(service.getByAcp(acp)).thenReturn(orders);

        List<Order> returned_list_orders = service.getByAcp(acp);
        assertThat(returned_list_orders.get(0).getAcp().equals(acp));
        assertThat(returned_list_orders.get(1).getAcp().equals(acp));
        assertThat(returned_list_orders.get(2).getAcp().equals(acp));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByAcp(acp);
    }

    @Test
    void whenGetByInvalidAcp_returnNull(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);

        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        Mockito.when(service.getByAcp(acp)).thenReturn(orders);

        ACP acp_fake = new ACP("Loja ACP fake", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        List<Order> returned_list_orders = service.getByAcp(acp_fake);
        assertThat(returned_list_orders).isEmpty();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByAcp(acp_fake);
    }

    @Test
    void whenGetByValidTrackingNumber_returnOrderWithTrackingNumber(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);

        order.settrackingNumber("1");
        Mockito.when(service.getByTrackingNumber("1")).thenReturn(order);

        Order returned_order = service.getByTrackingNumber("1");
        assertThat(returned_order.gettrackingNumber().equals("1"));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByTrackingNumber("1");
    }

    @Test
    void whenGetByInvalidTrackingNumber_returnNull(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);

        order.settrackingNumber("1");
        Mockito.when(service.getByTrackingNumber("1")).thenReturn(order);

        Order returned_order = service.getByTrackingNumber("-1");
        assertThat(returned_order).isNull();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByTrackingNumber("-1");
    }

    @Test
    void whenchangeState_STORE_to_COURIER_thenReturnTrueAndStateIsCOURIER() throws Exception{
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);
        order.settrackingNumber("1");

        Mockito.when(service.getByTrackingNumber("1")).thenReturn(order);

        boolean result = service.changeState_STORE_to_COURIER("1", 123456L);
        assertThat(result).isTrue();
        assertThat(order.getStatus()).isEqualTo("COURIER");

        Mockito.verify(repository, VerificationModeFactory.times(1)).findByTrackingNumber("1"); 
    }

    @Test
    void whenchangeState_COURIER_to_ACPPOINT_thenReturnTrueAndStateIsACPPOINT() throws Exception{
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);
        order.settrackingNumber("1");
        order.setStatus("COURIER");

        Mockito.when(service.getByTrackingNumber("1")).thenReturn(order);

        boolean result = service.changeState_COURIER_to_ACPPOINT("1", 123456L);
        assertThat(result).isTrue();
        assertThat(order.getStatus()).isEqualTo("ACP_POINT");

        Mockito.verify(repository, VerificationModeFactory.times(1)).findByTrackingNumber("1");
    }

    @Test
    void whenchangeState_ACPOINT_to_COLLECTED_thenReturnTrueAndStateIsCOLLECTED(){
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Order order = new Order("cliente", acp);
        order.settrackingNumber("1");
        order.setStatus("ACP_POINT");

        Mockito.when(service.getByTrackingNumber("1")).thenReturn(order);

        boolean result = service.changeState_ACPPOINT_to_COLLECTED("1", 123456L);
        assertThat(result).isTrue();
        assertThat(order.getStatus()).isEqualTo("COLLECTED");

        Mockito.verify(repository, VerificationModeFactory.times(1)).findByTrackingNumber("1");
        
    }

}

