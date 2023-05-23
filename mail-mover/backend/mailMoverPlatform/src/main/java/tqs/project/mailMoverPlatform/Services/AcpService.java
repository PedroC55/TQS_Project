package tqs.project.mailMoverPlatform.services;

import java.util.List;
import java.util.Optional;

import tqs.project.mailMoverPlatform.entities.ACP;

public interface AcpService {
    
    public ACP addACP(ACP acp);
    public Optional<ACP> getAcpById(Long id);
    public List<ACP> getAllAcps();
    public boolean performLogin(String email, String password);
}
