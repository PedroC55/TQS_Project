package tqs.project.mailMoverPlatform.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.repositories.AdminRepository;
import tqs.project.mailMoverPlatform.entities.Admin;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository repository;
    @Override
    public Admin addAdmin(Admin admin){
        return repository.save(admin);
    }
    @Override
    public boolean performLogin(String email, String password){
        Optional<Admin> admin =repository.findByEmail(email);
        if (!admin.isPresent()){
            return false;
        }
        String pw = admin.get().getPassword();
        if (pw.equals(password)){
            return true;
        }
        return false;
    }
    
    
}
