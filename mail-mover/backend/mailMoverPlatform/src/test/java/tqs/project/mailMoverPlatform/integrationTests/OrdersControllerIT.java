package tqs.project.mailMoverPlatform.integrationTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.repositories.OrderRepository;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrdersControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private AcpServiceImpl acpService;
    
    @Autowired
    OrderRepository order_repository;

    @Autowired
    AcpRepository acp_repository;

    @BeforeEach
    void setUp(){
        order_repository.deleteAll();
        acp_repository.deleteAll();
    }
    @Test
    public void testCreateOrder() {
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp = acpService.addACP(acp);

        Order order = new Order("Client1", acp);

        ResponseEntity<Object> response = restTemplate.postForEntity(getBaseUrl() + "/v1/orders/new/{clientName}/{acp_id}",
                order, Object.class, order.getclientName(), acp.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(HashMap.class);

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertThat(responseBody.containsKey("id")).isTrue();
        assertThat(responseBody.containsKey("clientName")).isTrue();
        assertThat(responseBody.containsKey("status")).isTrue();
        assertThat(responseBody.containsKey("storePickUpDate")).isTrue();
        assertThat(responseBody.containsKey("acpDeliveryDate")).isTrue();
        assertThat(responseBody.containsKey("clientPickUpDate")).isTrue();
        assertThat(responseBody.containsKey("acp")).isTrue();

        HashMap<String, Object> acpInfo = (HashMap<String, Object>) responseBody.get("acp");
        assertThat(acpInfo.containsKey("id")).isTrue();
        assertThat(acpInfo.containsKey("name")).isTrue();
        assertThat(acpInfo.containsKey("address")).isTrue();
        assertThat(acpInfo.containsKey("email")).isTrue();
    }

    @Test
    public void testGetAllOrders() {
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp = acpService.addACP(acp);

        Order order1 = new Order("Client1", acp);
        Order order2 = new Order("Client2", acp);

        order_repository.save(order1);
        order_repository.save(order2);
        
        ResponseEntity<Object> response = restTemplate.getForEntity(getBaseUrl() + "/v1/orders/all", Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(ArrayList.class);

        ArrayList<HashMap<String, Object>> responseBody = (ArrayList<HashMap<String, Object>>) response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.size(), is(2));
        assertThat(responseBody.get(0).get("clientName"), is("Client1"));
    }

    @Test
    public void testGetOrdersByAcpId() {
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp = acpService.addACP(acp);
        Order order = new Order("Client1", acp);
        order_repository.save(order);

        ResponseEntity<Object> response = restTemplate.getForEntity(getBaseUrl() + "/v1/orders/byAcp/{acp_id}",
                Object.class, acp.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(ArrayList.class);

        ArrayList<HashMap<String, Object>> responseBody = (ArrayList<HashMap<String, Object>>) response.getBody();
        
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.get(0).get("clientName"), is("Client1"));
    }

    @Test
    public void testGetOrderById() {
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");
        acp = acpService.addACP(acp);
        Order order = new Order("Client1", acp);
        order = orderService.addOrder(order);

        ResponseEntity<Object> response = restTemplate.getForEntity(getBaseUrl() + "/v1/orders/byId/{id}",
                Object.class, order.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(HashMap.class);

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertThat(responseBody.containsKey("id")).isTrue();
        assertThat(responseBody.containsKey("clientName")).isTrue();
        assertThat(responseBody.containsKey("status")).isTrue();
        assertThat(responseBody.containsKey("storePickUpDate")).isTrue();
        assertThat(responseBody.containsKey("acpDeliveryDate")).isTrue();
        assertThat(responseBody.containsKey("clientPickUpDate")).isTrue();
        assertThat(responseBody.containsKey("acp")).isTrue();
        assertThat(responseBody.get("clientName"), is("Client1"));
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
