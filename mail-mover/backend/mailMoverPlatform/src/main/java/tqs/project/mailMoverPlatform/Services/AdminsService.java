package tqs.project.mailMoverPlatform.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.Entities.Admin;
import tqs.project.mailMoverPlatform.Repositories.AdminRepository;

@Service
public class AdminsService {
    @Autowired
    AdminRepository repository;
     
    public Admin addAdmin(Admin admin){
        return null;
    }
    public boolean performLogin(String email, String password){
        return false;
    }
    
    
}
