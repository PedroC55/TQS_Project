package tqs.project.mailMoverPlatform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.Entities.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,String>{
    Admin findByEmail(String email);
}
