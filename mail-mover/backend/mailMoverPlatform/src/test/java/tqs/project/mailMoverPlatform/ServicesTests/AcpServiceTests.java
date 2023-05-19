package tqs.project.mailMoverPlatform.ServicesTests;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.services.AcpService;

public class AcpServiceTests {
    @Mock
    private AcpRepository repository;
    @InjectMocks
    private AcpService service;
    
    @Test
    void whenAddValidACP_thenReturnAcpWithValidId(){

    }

    @Test
    void whenGetAcpByValidId_thenReturnAcpNameAndAddress(){

    }
    @Test
    void whenGetAcpByInvalidId_thenReturnEmpty(){
        
    }

    @Test
    void whenGetAllAcp_thenReturnListOfAcpsNameAndAddress(){

    }

    @Test
    void whenPerformLoginWithValidCredentials_thenReturnTrue(){

    }

    @Test
    void whenPerformLoginWithInvalidCredentials_thenReturnFalse(){

    }
}
