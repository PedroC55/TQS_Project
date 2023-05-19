package tqs.project.mailMoverPlatform.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.entities.Admin;

@Repository
public interface AdminRepository  extends MongoRepository<Admin,String>{
    Admin findById();
    List<Admin> findAll();
    Admin findByEmail(String email);
}
