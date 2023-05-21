package tqs.project.mailMoverPlatform.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.repositories.AcpRepository;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.ACP_public;

@Service
public class AcpService {
    @Autowired
    AcpRepository repository;

    public ACP addACP(ACP acp){
        return repository.save(acp);
    }
     
    public ACP_public getNameAndAddressById(Long id){
        return null;
    }

    public List<ACP_public> getAllAcpsNameAndAddress(){
        return null;
    }

    public boolean performLogin(String email, String password){
        return false;
    }
    
}
