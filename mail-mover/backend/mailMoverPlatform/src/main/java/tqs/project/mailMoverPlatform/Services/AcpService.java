package tqs.project.mailMoverPlatform.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.Entities.ACP;
import tqs.project.mailMoverPlatform.Repositories.AcpRepository;

@Service
public class AcpService {
    @Autowired
    AcpRepository repository;

    public ACP addACP(ACP acp){

    }
    public ACP getNameAndAddressById(String id){

    }
    public List<ACP> getAllAcpsNameAndAddress(){

    }

    public boolean performLogin(String email, String password){

    }
    
}
