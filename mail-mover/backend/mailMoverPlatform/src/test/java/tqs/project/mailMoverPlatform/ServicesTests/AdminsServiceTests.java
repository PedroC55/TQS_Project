package tqs.project.mailMoverPlatform.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.project.mailMoverPlatform.entities.Admin;
import tqs.project.mailMoverPlatform.repositories.AdminRepository;
import tqs.project.mailMoverPlatform.services.AdminServiceImpl;


@ExtendWith(MockitoExtension.class)
class AdminsServiceTests {
    
    @Mock
    private AdminRepository repository;
    @InjectMocks
    private AdminServiceImpl service;

    @Test
    void whenAddValidAdmin_thenReturnAdminWithValidId(){
        Admin admin = new Admin("admin@mail.com", "pw_admin");
        admin.setId(111L);
        Mockito.when(service.addAdmin(admin)).thenReturn(admin);

        Admin savedAdmin = service.addAdmin(admin);
        assertThat(savedAdmin.getId().equals(admin.getId()));
        Mockito.verify(repository, VerificationModeFactory.times(1)).save(admin);
    }

    @Test
    void whenPerformLoginWithValidCredentials_thenReturnTrue(){
        Admin admin = new Admin("admin@mail.com", "pw_admin");
        Mockito.when(service.performLogin(admin.getEmail(),admin.getPassword())).thenReturn(true);

        Boolean isAuthenticated = service.performLogin("admin@mail.com", "pw_admin");

        assertThat(isAuthenticated.equals(true));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByEmail(admin.getEmail());
    }

    @Test
    void whenPerformLoginWithInvalidCredentials_thenReturnFalse(){
        Admin admin = new Admin("admin@mail.com", "pw_admin");
        Mockito.when(service.performLogin(admin.getEmail(),"fakepw")).thenReturn(false);

        Boolean isAuthenticated = service.performLogin("admin@mail.com", "fakepw");

        assertThat(isAuthenticated.equals(false));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByEmail(admin.getEmail());
    }
}
