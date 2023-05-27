package tqs.project.mailMoverPlatform.controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tqs.project.mailMoverPlatform.controller.OrdersController;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@WebMvcTest(OrdersController.class)
public class OrdersControllerMockMvcTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private AcpServiceImpl acpService;

    @Test
    public void testCreateOrder() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);
        given(acpService.getAcpById(1L)).willReturn(Optional.of(acp));

        Order order = new Order("cliente1", acp);
        order.setId(1L);
        given(orderService.addOrder(order)).willReturn(order);

        mockMvc.perform(post("/v1/orders/new/{clientname}/{acp_id}", "cliente1", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clientName").value("Client1"))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.acp.id").value(1))
                .andExpect(jsonPath("$.acp.name").value("Acp1"))
                .andExpect(jsonPath("$.acp.address").value("Address1"))
                .andExpect(jsonPath("$.acp.email").value("acp1@mail.com"));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        Order order1 = new Order("cliente1", acp);
        order1.setId(1L);
        Order order2 = new Order("cliente2", acp);
        order2.setId(2L);

        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        allOrders.add(order2);

        given(orderService.getAllOrders()).willReturn(allOrders);

        mockMvc.perform(get("/v1/orders/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].clientName").value("cliente1"))
                .andExpect(jsonPath("$[0].status").value("STORE"))
                .andExpect(jsonPath("$[0].acp.id").value(1L))
                .andExpect(jsonPath("$[0].acp.name").value("Loja ACP"))
                .andExpect(jsonPath("$[0].acp.address").value("Rua dos correios"))
                .andExpect(jsonPath("$[0].acp.email").value("lojaAcp@mail.com"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].clientName").value("cliente2"))
                .andExpect(jsonPath("$[1].status").value("STORE"))
                .andExpect(jsonPath("$[1].acp.id").value(1L))
                .andExpect(jsonPath("$[1].acp.name").value("Loja ACP"))
                .andExpect(jsonPath("$[1].acp.address").value("Rua dos correios"))
                .andExpect(jsonPath("$[1].acp.email").value("lojaAcp@mail.com"));
    }

    @Test
    public void testGetByAcpId() throws Exception {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        given(acpService.getAcpById(1L)).willReturn(Optional.of(acp));

        Order order1 = new Order("cliente1", acp);
        order1.setId(1L);
        Order order2 = new Order("cliente2", acp);
        order2.setId(2L);

        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        allOrders.add(order2);

        given(orderService.getByAcp(acp)).willReturn(allOrders);

        mockMvc.perform(get("/v1/orders/byAcp/{acp_id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].clientName").value("cliente1"))
                .andExpect(jsonPath("$[0].status").value("STORE"))
                .andExpect(jsonPath("$[0].acp.id").value(1L))
                .andExpect(jsonPath("$[0].acp.name").value("Loja ACP"))
                .andExpect(jsonPath("$[0].acp.address").value("Rua dos correios"))
                .andExpect(jsonPath("$[0].acp.email").value("lojaAcp@mail.com"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].clientName").value("cliente2"))
                .andExpect(jsonPath("$[1].status").value("STORE"))
                .andExpect(jsonPath("$[1].acp.id").value(1L))
                .andExpect(jsonPath("$[1].acp.name").value("Loja ACP"))
                .andExpect(jsonPath("$[1].acp.address").value("Rua dos correios"))
                .andExpect(jsonPath("$[1].acp.email").value("lojaAcp@mail.com"));
    }

    @Test
    public void testGetById() throws Exception {
        // Mock the behavior of the orderService.getById() method
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(1L);

        Order order = new Order("cliente1", acp);
        order.setId(1L);
        given(orderService.getById(1L)).willReturn(order);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/v1/orders/byId/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.clientName").value("cliente1"))
                .andExpect(jsonPath("$.status").value("STORE"))
                .andExpect(jsonPath("$.acp.id").value(1L))
                .andExpect(jsonPath("$.acp.name").value("Loja ACP"))
                .andExpect(jsonPath("$.acp.address").value("Rua dos correios"))
                .andExpect(jsonPath("$.acp.email").value("lojaAcp@mail.com"));
    }

    @Test
    public void testDeleteAllOrders() throws Exception {
        mockMvc.perform(get("/v1/orders/deleteAll"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteAll();
    }

    @Test
    public void testChangeState_STORE_to_COURIER() throws Exception {
        given(orderService.changeState_STORE_to_COURIER(1L, 123L)).willReturn(true);

        mockMvc.perform(get("/v1/orders/storeToCourier/{id}/{ts}", 1L, 123L))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testChangeState_COURIER_to_ACP() throws Exception {
        given(orderService.changeState_COURIER_to_ACPPOINT(1L, 123L)).willReturn(true);

        mockMvc.perform(get("/v1/orders/courierToAcp/{id}/{ts}", 1L, 123L))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testChangeState_ACP_to_COLLECTED() throws Exception {
        given(orderService.changeState_ACPPOINT_to_COLLECTED(1L, 123L)).willReturn(true);

        mockMvc.perform(get("/v1/orders/acpToCollected/{id}/{ts}", 1L, 123L))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}