package tqs.project.mailMoverPlatform.ServicesTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tqs.project.mailMoverPlatform.Repositories.AdminRepository;
import tqs.project.mailMoverPlatform.Services.AcpService;

public class AdminsServiceTests {
    
    @Mock
    private AdminRepository repository;
    @InjectMocks
    private AcpService service;

    @Test
    void whenAddValidAdmin_thenReturnAdminWithValidId(){

    }

    @Test
    void whenPerformLoginWithValidCredentials_thenReturnTrue(){

    }

    @Test
    void whenPerformLoginWithInvalidCredentials_thenReturnFalse(){

    }
}
