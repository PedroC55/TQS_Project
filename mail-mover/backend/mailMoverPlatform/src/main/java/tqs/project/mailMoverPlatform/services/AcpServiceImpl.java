package tqs.project.mailMoverPlatform.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.repositories.AcpRepository;


@Service
public class AcpServiceImpl implements AcpService{
    @Autowired
    AcpRepository repository;

    @Override
    public ACP addACP(ACP acp){
        return repository.save(acp);
    }
    @Override
    public Optional<ACP> getAcpById(Long id){
        return repository.findById(id);
    }
    @Override
    public List<ACP> getAllAcps(){
        return repository.findAll();
    }
    @Override
    public boolean performLogin(String email, String password){
        Optional<ACP> acp =repository.findByEmail(email);
        if (!acp.isPresent()){
            return false;
        }
        String pw = acp.get().getPassword();
        if (pw.equals(password)){
            return true;
        }
        return false;
    }
    
}
