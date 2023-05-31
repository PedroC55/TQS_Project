package tqs.project.mailMoverPlatform.integrationTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.is;
import java.util.List;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.repositories.OrderRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcpControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    AcpRepository repository;
    @Autowired
    OrderRepository order_repository;
    
    @BeforeEach
    void setUp(){
        order_repository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void testCreateACP() {
        ACP acp = new ACP("Loja ACP","Rua dos correios","lojaAcp@mail.com","pw_acp");

        ResponseEntity<ACP> response = restTemplate.postForEntity(getBaseUrl() + "/v1/acp/new", acp, ACP.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ACP createdAcp = response.getBody();
        assertThat(createdAcp).isNotNull();
        assertThat(createdAcp.getName().equals(acp.getName()));
        assertThat(createdAcp.getEmail().equals(acp.getEmail()));
        assertThat(createdAcp.getId()).isNotNull();
    }

    @Test
    public void testGetAllAcps() {
        ACP acp1 = new ACP("Loja ACP 1","Rua dos correios","lojaAcp1@mail.com","pw_acp1");
        ACP acp2 = new ACP("Loja ACP2 ","Rua da avenida","lojaAcp2@mail.com","pw_acp2");
        repository.save(acp1);
        repository.save(acp2);

        ResponseEntity<List> response = restTemplate.getForEntity(getBaseUrl() + "/v1/acp/all", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<HashMap<String, String>> acps = response.getBody();
        assertThat(acps).isNotEmpty();
        assertThat(acps.size(), is(2));
        
    }


    @Test
    public void testGetAcpById() {
        ACP acp1 = new ACP("Loja ACP 1","Rua dos correios","lojaAcp1@mail.com","pw_acp1");
        repository.save(acp1);
        Long acpId = acp1.getId();
        
        ResponseEntity<HashMap> response = restTemplate.getForEntity(getBaseUrl() + "/v1/acp/{id}", HashMap.class, acpId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HashMap<String, String> response_acp = response.getBody();
        assertThat(response_acp).isNotNull();
        assertThat(response_acp.get("id"),is(acpId.toString()));
    }

    @Test
    public void testLogin() {
        ACP acp1 = new ACP("Loja ACP 1","Rua dos correios","lojaAcp1@mail.com","pw_acp1");
        Long acpId = 1L;
        acp1.setId(acpId);
        repository.save(acp1);

        LoginInfo loginInfo = new LoginInfo("lojaAcp1@mail.com","pw_acp1");

        ResponseEntity<Boolean> response = restTemplate.postForEntity(getBaseUrl() + "/v1/acp/login", loginInfo, Boolean.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Boolean auth = response.getBody();
        assertThat(auth).isNotNull();
        assertThat(auth).isTrue();
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
    
