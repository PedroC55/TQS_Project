package tqs.project.mailMoverPlatform.Repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tqs.project.mailMoverPlatform.Entities.Admin;

@Repository
public interface AdminRepository  extends MongoRepository<Admin,String>{
    Admin findById();
    List<Admin> findAll();
    Admin findByEmail(String email);
}
