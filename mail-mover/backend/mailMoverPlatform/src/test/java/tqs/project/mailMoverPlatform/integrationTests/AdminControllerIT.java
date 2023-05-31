package tqs.project.mailMoverPlatform.integrationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.entities.LoginInfo;
import tqs.project.mailMoverPlatform.repositories.AdminRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    
    @Autowired
    AdminRepository repository;
    
    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    public void testCreateAdmin() {
        Admin admin = new Admin("admin2@mail.com", "admin2_pw");
        
        ResponseEntity<Admin> response = restTemplate.postForEntity(getBaseUrl() + "/v1/admin/new", admin, Admin.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Admin createdAdmin = response.getBody();
        assertThat(createdAdmin).isNotNull();
        assertThat(createdAdmin.getEmail().equals(admin.getEmail()));
        assertThat(createdAdmin.getId()).isNotNull();
    }

    @Test
    public void testAdminLogin() {
        Admin admin = new Admin("admin1@mail.com", "admin1_pw");
        repository.save(admin);
        LoginInfo loginInfo = new LoginInfo("admin1@mail.com", "admin1_pw");

        ResponseEntity<Boolean> response = restTemplate.postForEntity(getBaseUrl() + "/v1/admin/login", loginInfo, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Boolean auth = response.getBody();
        assertThat(auth).isNotNull();
        assertThat(auth).isTrue();
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
