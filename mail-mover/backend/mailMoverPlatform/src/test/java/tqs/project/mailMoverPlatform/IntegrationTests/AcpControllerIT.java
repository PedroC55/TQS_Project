package tqs.project.mailMoverPlatform.integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.List;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.LoginInfo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcpControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


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
        ResponseEntity<List> response = restTemplate.getForEntity(getBaseUrl() + "/v1/acp/all", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<HashMap<String, String>> acps = response.getBody();
        assertThat(acps).isNotEmpty();
    }


    @Test
    public void testGetAcpById() {
        Long acpId = 1L;
        ResponseEntity<HashMap> response = restTemplate.getForEntity(getBaseUrl() + "/v1/acp/{id}", HashMap.class, acpId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HashMap<String, String> acp = response.getBody();
        assertThat(acp).isNotNull();
        assertThat(acp.get("id").equals(acpId.toString()));

    }

    @Test
    public void testLogin() {
        LoginInfo loginInfo = new LoginInfo("lojaAcp@mail.com","pw_acp");

        ResponseEntity<HashMap> response = restTemplate.postForEntity(getBaseUrl() + "/v1/acp/login", loginInfo, HashMap.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HashMap<String, Object> responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        Boolean auth = (Boolean) responseBody.get("auth");
        assertThat(auth).isNotNull();
        assertThat(auth).isTrue();
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
    
