package tqs.project.mailMoverPlatform.Repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.project.mailMoverPlatform.Entities.ACP;

@Repository
public interface AcpRepository  extends JpaRepository<ACP,String>{
    Optional<ACP> findById(String id);
}
