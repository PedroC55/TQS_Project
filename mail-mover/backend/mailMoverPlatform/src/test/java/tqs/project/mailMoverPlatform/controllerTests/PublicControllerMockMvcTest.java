package tqs.project.mailMoverPlatform.controllerTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.project.mailMoverPlatform.controller.PublicController;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@WebMvcTest(PublicController.class)
public class PublicControllerMockMvcTest {
    
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

        Order order = new Order("Client1", acp);
        order.setId(1L);
        given(orderService.addOrder(any())).willReturn(order);

        mockMvc.perform(post("/v1/mailMover/new/{clientname}/{acp_id}", "cliente1", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clientName").value("Client1"))
                .andExpect(jsonPath("$.status").value("STORE"))
                .andExpect(jsonPath("$.acp.id").value(acp.getId()))
                .andExpect(jsonPath("$.acp.name").value(acp.getName()))
                .andExpect(jsonPath("$.acp.address").value(acp.getAddress()))
                .andExpect(jsonPath("$.acp.email").value(acp.getEmail()));
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

        mockMvc.perform(get("/v1/mailMover/byAcp/{acp_id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
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
        mockMvc.perform(get("/v1/mailMover/byId/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
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
    public void testChangeState_STORE_to_COURIER() throws Exception {
        given(orderService.changeState_STORE_to_COURIER(1L, 123L)).willReturn(true);

        mockMvc.perform(get("/v1/mailMover/storeToCourier/{id}/{ts}", 1L, 123L))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    
    @Test
    public void testGetAllAcps() throws Exception {
        ACP acp1 = new ACP("Loja ACP 1", "Rua dos correios 1", "lojaAcp1@mail.com", "pw_acp");
        acp1.setId(1L);

        ACP acp2 = new ACP("Loja ACP 2", "Rua dos correios 2", "lojaAcp2@mail.com", "pw_acp");
        acp2.setId(2L);

        List<ACP> allAcps = new ArrayList<>();
        allAcps.add(acp1);
        allAcps.add(acp2);

        given(acpService.getAllAcps()).willReturn(allAcps);

        mockMvc.perform(get("/v1/mailMover/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists());
    }
}
