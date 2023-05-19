package tqs.project.mailMoverPlatform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.repositories.AdminRepository;
import tqs.project.mailMoverPlatform.entities.Admin;

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
