package tqs.project.mailMoverPlatform.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.Entities.ACP;
import tqs.project.mailMoverPlatform.Entities.ACP_public;
import tqs.project.mailMoverPlatform.Repositories.AcpRepository;

@Service
public class AcpService {
    @Autowired
    AcpRepository repository;

    public ACP addACP(ACP acp){
        return repository.save(acp);
    }
     
    public ACP_public getNameAndAddressById(String id){
        
    }

    public List<ACP_public> getAllAcpsNameAndAddress(){

    }

    public boolean performLogin(String email, String password){

    }
    
}
