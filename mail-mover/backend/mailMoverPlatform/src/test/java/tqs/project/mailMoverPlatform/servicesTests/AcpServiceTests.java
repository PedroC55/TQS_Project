package tqs.project.mailMoverPlatform.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.services.AcpServiceImpl;

@ExtendWith(MockitoExtension.class)
class AcpServiceTests {
    
    @Mock(lenient = true)
    private AcpRepository repository;
    
    @InjectMocks AcpServiceImpl service;

    @Test
    void whenAddValidACP_thenReturnAcpWithValidId() {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Mockito.when(repository.save(acp)).thenReturn(acp);
        acp.setId(111L);

        ACP savedAcp = service.addACP(acp);
        assertThat(savedAcp.getId().equals(acp.getId()));
        Mockito.verify(repository, VerificationModeFactory.times(1)).save(acp);
    }

    @Test
    void whenGetAcpByValidId_thenReturnAcp() {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        acp.setId(111L);
        Mockito.when(repository.findById(111L)).thenReturn(Optional.of(acp));

        Optional<ACP> returned_acp = service.getAcpById(111L);
        assertThat(returned_acp.get().getId().equals(acp.getId()));
        assertThat(returned_acp.get().getEmail().equals(acp.getEmail()));

        Mockito.verify(repository, VerificationModeFactory.times(1)).findById(acp.getId());
    }

    @Test
    void whenGetAcpByInvalidId_thenReturnNull() {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Mockito.when(repository.findById(acp.getId())).thenReturn(Optional.of(acp));

        Optional<ACP> returned_acp = service.getAcpById(-1L);
        assertThat(returned_acp).isNotPresent();
        Mockito.verify(repository, VerificationModeFactory.times(1)).findById(-1L);
    }

    @Test
    void whenGetAllAcp_thenReturnListOfAcps() {
        ACP acp1 = new ACP("Loja ACP 1", "Rua dos correios", "lojaAcp1@mail.com", "pw_acp1");
        ACP acp2 = new ACP("Loja ACP 2", "Avenida da Liberdade", "lojaAcp2@mail.com", "pw_acp2");
        ACP acp3 = new ACP("Loja ACP 3", "Rua Mário Sacramento", "lojaAcp3@mail.com", "pw_acp3");
        List<ACP> allACPs = new ArrayList<>();
        allACPs.add(acp1);
        allACPs.add(acp2);
        allACPs.add(acp3);
        Mockito.when(repository.findAll()).thenReturn(allACPs);

        List<ACP> returned_list_acp = service.getAllAcps();

        assertThat(returned_list_acp).containsExactly(acp1, acp2, acp3);
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    void whenPerformLoginWithValidCredentials_thenReturnTrue() {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Mockito.when(repository.findByEmail(acp.getEmail())).thenReturn(Optional.of(acp));

        Boolean isAuthenticated = service.performLogin("lojaAcp@mail.com", "pw_acp");
        
        assertThat(isAuthenticated.equals(true));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByEmail(acp.getEmail());
    }

    @Test
    void whenPerformLoginWithInvalidCredentials_thenReturnFalse() {
        ACP acp = new ACP("Loja ACP", "Rua dos correios", "lojaAcp@mail.com", "pw_acp");
        Mockito.when(repository.findByEmail(acp.getEmail())).thenReturn(Optional.of(acp));

        Boolean isAuthenticated = service.performLogin("lojaAcp@mail.com", "fakepw");
        
        assertThat(isAuthenticated.equals(false));
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByEmail(acp.getEmail());
    }
}
