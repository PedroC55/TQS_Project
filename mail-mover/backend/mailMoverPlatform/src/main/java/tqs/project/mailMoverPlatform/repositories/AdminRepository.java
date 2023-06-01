package tqs.project.mailMoverPlatform.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.entities.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Long>{
    Optional<Admin> findById(Long id);
    List<Admin> findAll();
    Optional<Admin> findByEmail(String email);
}
